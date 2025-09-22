package com.example.splitwiseapp.expenses;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction_table")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private int groupId;  //Where to add
    private int addedBy; // who is adding
    private int paidBy; //who is paid
    private String message; //description paid for
    private BigDecimal amount;
    private String splitType;  // Type of split mean is it percentage type or amount or
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    private int numberOfParticipantsSplit;
}
