package com.example.splitwiseapp.settleUpAmounts;

import com.example.splitwiseapp.addingFriends.AddingFriendsRepository;
import com.example.splitwiseapp.expenses.Split;
import com.example.splitwiseapp.expenses.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class SettleUpService {

    @Autowired
    private AddingFriendsRepository addingFriendsRepository;

    @Autowired
    private SplitRepository splitRepository;

    // Settlement DTO
    public static class Settlement {
        public int fromUserId;
        public int toUserId;
        public BigDecimal amount;

        public Settlement(int fromUserId, int toUserId, BigDecimal amount) {
            this.fromUserId = fromUserId;
            this.toUserId = toUserId;
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    // Settle up within one group
    public List<Settlement> settleGroupAmounts(int groupId) {
        List<Integer> memberIds = getMembersOfGroup(groupId);
        Map<Integer, BigDecimal> netBalances = computeNetBalances(memberIds, groupId);
        return minimizeTransactions(netBalances);
    }

    // Settle up for a user across all groups they belong to
    public List<Settlement> settleUserAmounts(int userId) {
        List<Integer> groupIds = getGroupsOfUser(userId);

        // Collect all unique memberIds across these groups
        Set<Integer> allMembers = new HashSet<>();
        for (Integer groupId : groupIds) {
            allMembers.addAll(getMembersOfGroup(groupId));
        }

        // Compute net balances for all members combining all relevant groups
        Map<Integer, BigDecimal> netBalances = new HashMap<>();
        for (Integer memberId : allMembers) {
            netBalances.put(memberId, BigDecimal.ZERO);
        }

        for (Integer groupId : groupIds) {
            for (Integer memberId : getMembersOfGroup(groupId)) {
                List<Split> splits = splitRepository.findByGroupIdAndUserId(groupId, memberId);
                BigDecimal owes = BigDecimal.ZERO;
                BigDecimal owed = BigDecimal.ZERO;

                for (Split split : splits) {
                    if (split.getAmountOwed() != null) owes = owes.add(split.getAmountOwed());
                    if (split.getAmountPaid() != null) owed = owed.add(split.getAmountPaid());
                }

                netBalances.put(memberId,
                        netBalances.get(memberId).add(owed.subtract(owes)).setScale(2, RoundingMode.HALF_UP));
            }
        }
        return minimizeTransactions(netBalances);
    }

    // Helper: get all members of a given groupId
    private List<Integer> getMembersOfGroup(int groupId) {
        return addingFriendsRepository.findAll()
                .stream()
                .filter(m -> m.getGroupId() == groupId)
                .map(m -> m.getUserId())
                .distinct()
                .toList();
    }

    // Helper: get all group IDs for a given userId
    private List<Integer> getGroupsOfUser(int userId) {
        return addingFriendsRepository.findAll()
                .stream()
                .filter(m -> m.getUserId() == userId)
                .map(m -> m.getGroupId())
                .distinct()
                .toList();
    }

    // Calculate net balances for given members and one group
    private Map<Integer, BigDecimal> computeNetBalances(List<Integer> memberIds, int groupId) {
        Map<Integer, BigDecimal> netBalances = new HashMap<>();
        for (Integer memberId : memberIds) {
            List<Split> userSplits = splitRepository.findByGroupIdAndUserId(groupId, memberId);
            BigDecimal totalOwe = BigDecimal.ZERO;
            BigDecimal totalOwed = BigDecimal.ZERO;
            for (Split split : userSplits) {
                if (split.getAmountOwed() != null) totalOwe = totalOwe.add(split.getAmountOwed());
                if (split.getAmountPaid() != null) totalOwed = totalOwed.add(split.getAmountPaid());
            }
            netBalances.put(memberId, totalOwed.subtract(totalOwe).setScale(2, RoundingMode.HALF_UP));
        }
        return netBalances;
    }

    // Greedy method to minimize transactions given net balances map
    private List<Settlement> minimizeTransactions(Map<Integer, BigDecimal> netBalances) {
        PriorityQueue<Map.Entry<Integer, BigDecimal>> creditors = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );
        PriorityQueue<Map.Entry<Integer, BigDecimal>> debtors = new PriorityQueue<>(
                (a, b) -> a.getValue().compareTo(b.getValue())
        );

        for (var entry : netBalances.entrySet()) {
            if (entry.getValue().compareTo(BigDecimal.ZERO) > 0) {
                creditors.offer(entry);
            } else if (entry.getValue().compareTo(BigDecimal.ZERO) < 0) {
                debtors.offer(entry);
            }
        }

        List<Settlement> settlements = new ArrayList<>();
        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            var creditorEntry = creditors.poll();
            var debtorEntry = debtors.poll();

            int creditorId = creditorEntry.getKey();
            BigDecimal creditorBalance = creditorEntry.getValue();

            int debtorId = debtorEntry.getKey();
            BigDecimal debtorBalance = debtorEntry.getValue();

            BigDecimal amount = creditorBalance.min(debtorBalance.abs());

            settlements.add(new Settlement(debtorId, creditorId, amount));

            BigDecimal updatedCreditorBalance = creditorBalance.subtract(amount);
            BigDecimal updatedDebtorBalance = debtorBalance.add(amount);

            if (updatedCreditorBalance.compareTo(BigDecimal.ZERO) > 0) {
                creditors.offer(Map.entry(creditorId, updatedCreditorBalance));
            }
            if (updatedDebtorBalance.compareTo(BigDecimal.ZERO) < 0) {
                debtors.offer(Map.entry(debtorId, updatedDebtorBalance));
            }
        }
        return settlements;
    }

}
