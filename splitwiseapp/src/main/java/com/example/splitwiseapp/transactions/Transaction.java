package com.example.splitwiseapp.transactions;

import com.example.splitwiseapp.expenses.Split;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction_table")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "group_id")
    private int groupId;  //Where to add
    private int addedBy; // who is adding
    @Column(name = "paid_by")
    private int paidById; //who is paid id
    @Transient
    private String emailOfPaidBy;  //who is paid mail
    private String message; //description paid for
    private BigDecimal amount;
    private String splitType;  // Type of split mean is it percentage type or amount or
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    private int numberOfParticipantsSplit;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaction_id", referencedColumnName = "transactionId")
    private List<Split> split;


}
