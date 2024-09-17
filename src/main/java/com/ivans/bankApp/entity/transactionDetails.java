package com.ivans.bankApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class transactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transact_id")
    private int transactId;

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to UserDetails
    private int userId;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "make_or_spend", nullable = false)
    private boolean makeOrSpend;  // True for make, false for spend

    @Column(name = "credit_or_debit", nullable = false)
    private boolean creditOrDebit;  // True for credit, false for debit

    @Column(name = "account_used", nullable = false)
    private String accountUsed;

}