package com.example.splitwiseapp.settleUpAmounts;

import com.example.splitwiseapp.expenses.Split;
import com.example.splitwiseapp.expenses.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class TotalAmountService {
    @Autowired
    private SplitRepository splitRepository;

    public TotalAmount totalAmountEachGroup(int groupId, int userId){
        TotalAmount newDetails = new TotalAmount();
        List<Split> newSplitList = splitRepository.findByGroupIdAndUserId(groupId, userId);
        BigDecimal totalAmountOwed = BigDecimal .ZERO;
        BigDecimal totalAmountOwe = BigDecimal .ZERO;
        for(Split split : newSplitList){
            totalAmountOwed = totalAmountOwed.add(split.getAmountOwed()!= null ? split.getAmountOwed() : BigDecimal.ZERO);
            totalAmountOwe = totalAmountOwe.add(split.getAmountPaid()!= null ? split.getAmountPaid() : BigDecimal.ZERO);
        }
        if(totalAmountOwed.compareTo(totalAmountOwe) == 0){
            newDetails.setTotalOwed(totalAmountOwed);
            newDetails.setTotalOwe(totalAmountOwe);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) > 0) {
            newDetails.setTotalOwed(totalAmountOwed.subtract(totalAmountOwe));
            newDetails.setTotalOwe(BigDecimal.ZERO);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) < 0) {
            newDetails.setTotalOwed(BigDecimal.ZERO);
            newDetails.setTotalOwe(totalAmountOwe.subtract(totalAmountOwed));
        }
        return newDetails;
    }
    public TotalAmount totalAmountAllGroups(int userId){
        TotalAmount totalDetails = new TotalAmount();
        List<Split> newSplitList = splitRepository.findByUserId(userId);
        BigDecimal totalAmountOwed = BigDecimal .ZERO;
        BigDecimal totalAmountOwe = BigDecimal .ZERO;
        for(Split split : newSplitList){
            totalAmountOwed = totalAmountOwed.add(split.getAmountOwed()!= null ? split.getAmountOwed() : BigDecimal.ZERO);
            totalAmountOwe = totalAmountOwe.add(split.getAmountPaid()!= null ? split.getAmountPaid() : BigDecimal.ZERO);
        }
        if(totalAmountOwed.compareTo(totalAmountOwe) == 0){
            totalDetails.setTotalOwed(totalAmountOwed);
            totalDetails.setTotalOwe(totalAmountOwe);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) > 0) {
            totalDetails.setTotalOwed(totalAmountOwed.subtract(totalAmountOwe));
            totalDetails.setTotalOwe(BigDecimal.ZERO);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) < 0) {
            totalDetails.setTotalOwed(BigDecimal.ZERO);
            totalDetails.setTotalOwe(totalAmountOwe.subtract(totalAmountOwed));
        }
        return totalDetails;
    }
}
