package com.example.splitwiseapp.settleUpAmounts;

import com.example.splitwiseapp.expenses.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;



public class TotalAmountEachGroupService {
    @Autowired
    private SplitRepository splitRepository;

    public TotalAmountEachGroup totalAmountEachGroup(BigDecimal groupId, BigDecimal userId){
        TotalAmountEachGroup newDetails = new TotalAmountEachGroup();

        newDetails.setTotalOwed(groupId);
        newDetails.setTotalOwe(userId);
        return newDetails;
    }
}

