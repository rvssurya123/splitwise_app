package com.example.splitwiseapp.expenses;

import com.example.splitwiseapp.groups.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    private GroupsRepository groupsRepository;

    //
    public void addTransaction(int groupId, int addingBy, Transaction transaction) {
        Transaction newTrans = new Transaction();
        newTrans.setAmount(transaction.getAmount());
        newTrans.setAddedBy(addingBy);
        newTrans.setMessage(transaction.getMessage());
        newTrans.setGroupId(groupId);
        Integer paidBy = transaction.getPaidBy();
        if(paidBy == null){
            newTrans.setPaidBy(addingBy);
        }
        else{
            newTrans.setPaidBy(paidBy);
        }
        newTrans.setSplitType(transaction.getSplitType());
        newTrans.setNumberOfParticipantsSplit(transaction.getNumberOfParticipantsSplit());
        transactionRepository.save(newTrans);

    }
}
