package com.example.splitwiseapp.settleUpAmounts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settle")
@Tag(
        name = "Settle Up",
        description = "APIs to calculate and view settlement transactions for groups and users."
)
public class SettleUpController {

    @Autowired
    private SettleUpService settleUpService;

    // Settle up for a given groupId
    @Operation(
            summary = "Settle group balances",
            description = "Calculates the minimum set of payments required to settle all balances within the specified group."
    )
    @GetMapping("/group/{groupId}")
    public List<SettleUpService.Settlement> settleGroup(@PathVariable int groupId) {
        return settleUpService.settleGroupAmounts(groupId);
    }

    // Settle up for a given userId (aggregating all groups user belongs to)
    @Operation(
            summary = "Settle user balances",
            description = "Aggregates all groups the user belongs to and computes the net settlements the user needs to pay or receive."
    )
    @GetMapping("/user/{userId}")
    public List<SettleUpService.Settlement> settleUser(@PathVariable int userId) {
        return settleUpService.settleUserAmounts(userId);
    }
}
