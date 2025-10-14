package com.example.splitwiseapp.settleUpAmounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalAmountEachGroupController {
    @Autowired
    private TotalAmountEachGroupService totalAmountEachGroupService;

    @GetMapping("/users/groups/{groupId}/{userId}")
    public TotalAmountEachGroup totalAmountEachGroup(@PathVariable int userId, @PathVariable int groupId){
        return totalAmountEachGroupService.totalAmountEachGroup(groupId, userId );
    }
}
