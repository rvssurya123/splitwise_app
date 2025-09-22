package com.example.splitwiseapp.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // add expenses into group
    //Inputs path varibles:- userId: addingBy, groupId: who is adding,
    //Json:- mail: who is paying, msg : , amount, splitType,
    @PostMapping ("/users/group/transaction/{groupId}/{addingBy}")
    public String addTransaction(@PathVariable int groupId, @PathVariable int addingBy, @RequestBody Transaction transaction){
        transactionService.addTransaction(groupId, addingBy, transaction);
        return "TransactionAdded";
    }
}
