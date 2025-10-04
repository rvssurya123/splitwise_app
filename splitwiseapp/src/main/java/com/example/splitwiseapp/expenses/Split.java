package com.example.splitwiseapp.expenses;

import com.example.splitwiseapp.transactions.Transaction;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "expense_splits")
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseSplitId;
    @Transient
    private String userMail;
    private int userId;
    private int groupId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    private int owedToUserId;
    private BigDecimal sharePercentage;
    private BigDecimal amountOwed;
    @Column(name = "amount_to_receive")
    private BigDecimal amountPaid;
}
