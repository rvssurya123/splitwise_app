package com.example.splitwiseapp.settleUpAmounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settle")
public class SettleUpController {

    @Autowired
    private SettleUpService settleUpService;

    // Settle up for a given groupId
    @GetMapping("/group/{groupId}")
    public List<SettleUpService.Settlement> settleGroup(@PathVariable int groupId) {
        return settleUpService.settleGroupAmounts(groupId);
    }

    // Settle up for a given userId (aggregating all groups user belongs to)
    @GetMapping("/user/{userId}")
    public List<SettleUpService.Settlement> settleUser(@PathVariable int userId) {
        return settleUpService.settleUserAmounts(userId);
    }
}
