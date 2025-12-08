package com.example.splitwiseapp.settleUpAmounts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Balances Overview",
        description = "APIs to get total amount owed or to receive for a user, either within a specific group or across all groups."
)
public class TotalAmountController {
    @Autowired
    private TotalAmountService totalAmountEachGroupService;

    // Get total owe or owed for group
    @Operation(
            summary = "Get user balance in group",
            description = "Returns the total amount the user owes or is owed within the specified group."
    )
    @GetMapping("/users/groups/{groupId}/{userId}")
    public TotalAmount totalAmountEachGroup(@PathVariable int userId, @PathVariable int groupId){
        return totalAmountEachGroupService.totalAmountEachGroup(groupId, userId );
    }

    // Get total owe or owed for user
    @Operation(
            summary = "Get user balance across all groups",
            description = "Returns the net amount the user owes or is owed across all groups they belong to."
    )
    @GetMapping("/users/{userId}")
    public TotalAmount totalAmountAllGroup(@PathVariable int userId){
        return totalAmountEachGroupService.totalAmountAllGroups(userId);
    }
}
