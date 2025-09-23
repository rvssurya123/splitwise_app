package com.example.splitwiseapp.expenses;

import com.example.splitwiseapp.groups.GroupsRepository;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        transactionRepository.save(newTrans);
        }

    private int getIdByMail(String paidBy) {
        Users optionalUser = usersRepository.findFirstByEmail(paidBy);
        int id = optionalUser.getUserId();
        return id;
    }

    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

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

