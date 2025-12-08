package com.example.splitwiseapp.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/splitwise")
@Tag(
        name = "Transaction Management",
        description = "Handles adding, updating, deleting, and retrieving transactions within groups."
)

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // to get all the transactions from a particular group
    @GetMapping("/transactions/group/{groupId}")
    public List<Transaction> getTransactionsByGroup(@PathVariable int groupId) {
        return transactionService.getAllTransactionsByGroup(groupId);
    }

    // add expenses into group
    //Inputs path variables:- userId: addingBy, groupId: who is adding,
    //Json:- mail: who is paying, msg : , amount, splitType,
    @PostMapping("/users/group/transaction/{groupId}/{addingBy}")
    public ResponseEntity<?> addTransaction(@PathVariable int groupId, @PathVariable int addingBy, @RequestBody Transaction transaction){
        try {
            transactionService.addTransaction(groupId, addingBy, transaction);
            return ResponseEntity.ok("TransactionAdded");
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    //Update Transaction
    //Input : PathVariables: userid: who is logged in and trying to update, groupId
    //json : transaction id and all the transaction details need to update
    @PatchMapping("/users/group/transaction/{groupId}/{adminId}")
    public boolean updateTransactionDetails(@PathVariable int groupId, @PathVariable int adminId, @RequestBody Transaction transaction){
        boolean value = transactionService.updateTransactionDetails(groupId, adminId, transaction);
        return value;
    }

    // Delete expenses or Transaction
    //Inputs PathVaribles: userid: who is logged in and trying to delete, groupId
    //Json: transaction id
    @DeleteMapping("/users/group/transaction/{groupId}/{adminId}")
    public String deleteTransaction(@PathVariable int groupId, @PathVariable int adminId, @RequestBody Transaction transaction){
        transactionService.deleteTransaction(transaction.getTransactionId());
        return "Deleted";
    }

}
