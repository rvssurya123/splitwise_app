package com.example.splitwiseapp.transactions;

import com.example.splitwiseapp.expenses.Split;
import com.example.splitwiseapp.expenses.SplitRepository;
import com.example.splitwiseapp.groups.GroupsRepository;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SplitRepository splitRepository;

    //
    public void addTransaction(int groupId, int addingBy, Transaction transaction) {
        Transaction newTrans = new Transaction();
        newTrans.setAmount(transaction.getAmount());
        newTrans.setAddedBy(addingBy);
        newTrans.setMessage(transaction.getMessage());
        newTrans.setGroupId(groupId);
        newTrans.setSplitType(transaction.getSplitType());
        newTrans.setNumberOfParticipantsSplit(transaction.getNumberOfParticipantsSplit());

        int paidById = getIdByMail(transaction.getEmailOfPaidBy());
        if(paidById == 0){
            newTrans.setPaidById(addingBy);
        }
        else{
            newTrans.setPaidById(paidById);
        }

        List<Split> splitAmount = new ArrayList<>();
        splitAmount = transaction.getSplit();

        List<Split> updatedSplitList = new ArrayList<>();
        updatedSplitList =  splitAndAddAmount(splitAmount, transaction.getAmount(), paidById);
        //saved the transation and we will get transactionId;
        Transaction savedTransaction = transactionRepository.save(newTrans);

        for(Split eachSplit : updatedSplitList){
            eachSplit.setTransaction(savedTransaction);
            splitRepository.save(eachSplit);
        }

        }

    private int getIdByMail(String paidBy) {
        Users optionalUser = usersRepository.findFirstByEmail(paidBy);
        int id = optionalUser.getUserId();
        return id;
    }

    // method for split process
    public List<Split> splitAndAddAmount(List<Split> splitList, BigDecimal amount, int paidBy) {
        List<Split> newSplitList = new ArrayList<>();
        for(Split eachSplit : splitList){

            eachSplit.setOwedToUserId(paidBy);

            int userId = getIdByMail(eachSplit.getUserMail());
            eachSplit.setUserId(userId);

            BigDecimal sharedAmount = mathCalculation(eachSplit.getSharePercentage(), amount);

            if (userId == paidBy){ eachSplit.setAmountPaid(amount.subtract(sharedAmount));}
            else {eachSplit.setAmountOwed(sharedAmount);}
            newSplitList.add(eachSplit);
        }
        return newSplitList;
    }

    // math calculation that who need to pay to whom and how much
    public BigDecimal mathCalculation(BigDecimal percentage, BigDecimal amount){
        BigDecimal sharedAmount = amount
                .multiply(percentage)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        return sharedAmount;
    }

    //
    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    //
    public boolean updateTransactionDetails(int groupId, int adminId, Transaction transaction) {
            Transaction existingTransaction = transactionRepository.findById(transaction.getTransactionId())
                    .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

            int id = getIdByMail(transaction.getEmailOfPaidBy());
            System.out.println(id);
            existingTransaction.setPaidById(id);
            existingTransaction.setMessage(transaction.getMessage());
            existingTransaction.setAmount(transaction.getAmount());
            existingTransaction.setSplitType(transaction.getSplitType());
            existingTransaction.setNumberOfParticipantsSplit(transaction.getNumberOfParticipantsSplit());
            transactionRepository.save(existingTransaction);
            return true;
    }

    // get all rows from transaction table
    public List<Transaction> getAllTransactionsByGroup(int groupId){
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        transactionRepository.findAll().forEach(transaction -> {if(transaction.getGroupId() == groupId){ transactionArrayList.add(transaction);}});
        System.out.println(transactionArrayList);
        return transactionArrayList;
    }
}

