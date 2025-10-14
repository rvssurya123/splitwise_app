package com.example.splitwiseapp.settleUpAmounts;

import com.example.splitwiseapp.expenses.Split;
import com.example.splitwiseapp.expenses.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class TotalAmountEachGroupService {
    @Autowired
    private SplitRepository splitRepository;

    public TotalAmountEachGroup totalAmountEachGroup(int groupId, int userId){
        TotalAmountEachGroup newDetails = new TotalAmountEachGroup();
        List<Split> newSplitList = splitRepository.findByGroupIdAndUserId(groupId, userId);
        BigDecimal totalAmountOwed = BigDecimal .ZERO;
        BigDecimal totalAmountOwe = BigDecimal .ZERO;
        for(Split split : newSplitList){
            totalAmountOwed = totalAmountOwed.add(split.getAmountOwed()!= null ? split.getAmountOwed() : BigDecimal.ZERO);
            totalAmountOwe = totalAmountOwe.add(split.getAmountPaid()!= null ? split.getAmountPaid() : BigDecimal.ZERO);
        }
        if(totalAmountOwed.compareTo(totalAmountOwe) == 0){
            newDetails.setTotalOwed(totalAmountOwed);
            newDetails.setTotalOwe(totalAmountOwe);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) > 0) {
            newDetails.setTotalOwed(totalAmountOwed.subtract(totalAmountOwe));
            newDetails.setTotalOwe(BigDecimal.ZERO);
        } else if (totalAmountOwed.compareTo(totalAmountOwe) < 0) {
            newDetails.setTotalOwed(BigDecimal.ZERO);
            newDetails.setTotalOwe(totalAmountOwe.subtract(totalAmountOwed));
        }
        return newDetails;
    }
}
