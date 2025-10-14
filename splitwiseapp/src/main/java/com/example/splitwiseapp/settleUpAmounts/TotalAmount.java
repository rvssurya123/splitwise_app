package com.example.splitwiseapp.settleUpAmounts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalAmount {
    private BigDecimal totalOwe;
    private BigDecimal totalOwed;
}
