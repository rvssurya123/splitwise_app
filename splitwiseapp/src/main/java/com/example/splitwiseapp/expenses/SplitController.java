package com.example.splitwiseapp.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SplitController {
    @Autowired
    private SplitService splitService;

//    @PostMapping("/users/groups/transaction/split/{transactionId}")
//    public List<Split> splitAndAddAmount(@PathVariable int transactionId, @RequestBody Split split){
//        List<Split> splitList = splitService.splitAndAddAmount(transactionId, split);
//        return splitList;
//    }

}
