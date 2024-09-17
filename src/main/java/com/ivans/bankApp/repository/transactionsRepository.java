package com.ivans.bankApp.repository;

import com.ivans.bankApp.entity.transactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface transactionsRepository extends JpaRepository<transactionDetails, Long> {

    List<transactionDetails> findByUserId(int id);
    transactionDetails findByTransactId(int id);
}