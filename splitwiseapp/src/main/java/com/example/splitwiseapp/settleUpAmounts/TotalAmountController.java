package com.example.splitwiseapp.settleUpAmounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalAmountController {
    @Autowired
    private TotalAmountService totalAmountEachGroupService;

    // Get total owe or owed for group
    @GetMapping("/users/groups/{groupId}/{userId}")
    public TotalAmount totalAmountEachGroup(@PathVariable int userId, @PathVariable int groupId){
        return totalAmountEachGroupService.totalAmountEachGroup(groupId, userId );
    }

    // Get total owe or owed for user
    @GetMapping("/users/{userId}")
    public TotalAmount totalAmountAllGroup(@PathVariable int userId){
        return totalAmountEachGroupService.totalAmountAllGroups(userId);
    }
}
