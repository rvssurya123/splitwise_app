package com.example.splitwiseapp.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // add expenses into group
    //Inputs path variables:- userId: addingBy, groupId: who is adding,
    //Json:- mail: who is paying, msg : , amount, splitType,
    @PostMapping ("/users/group/transaction/{groupId}/{addingBy}")
    public String addTransaction(@PathVariable int groupId, @PathVariable int addingBy, @RequestBody Transaction transaction){
        transactionService.addTransaction(groupId, addingBy, transaction);
        return "TransactionAdded";
    }

    // Delete expenses or Transaction
    //Inputs PathVaribles: userid: who is logged in and trying to delete, groupId
    //Json: transaction id
    @DeleteMapping("/users/group/transaction/{groupId}/{adminId}")
    public String deleteTransaction(@PathVariable int groupId, @PathVariable int adminId, @RequestBody Transaction transaction){
        transactionService.deleteTransaction(transaction.getTransactionId());
        return "Deleted";
    }

    //Update Transaction
    //Input : PathVariables: userid: who is logged in and trying to update, groupId
    //json : transaction id and all the transaction details need to update
    @PatchMapping("/users/group/transaction/{groupId}/{adminId}")
    public boolean updateTransactionDetails(@PathVariable int groupId, @PathVariable int adminId, @RequestBody Transaction transaction){
        boolean value = transactionService.updateTransactionDetails(groupId, adminId, transaction);
        return value;
    }

    // to get all the transactions from a particular group
    @GetMapping("/transactions/group/{groupId}")
    public List<Transaction> getTransactionsByGroup(@PathVariable int groupId) {
        return transactionService.getAllTransactionsByGroup(groupId);
    }

}
