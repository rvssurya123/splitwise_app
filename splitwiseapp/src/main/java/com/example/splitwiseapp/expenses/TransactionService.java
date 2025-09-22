package com.example.splitwiseapp.expenses;

import com.example.splitwiseapp.groups.GroupsRepository;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}

