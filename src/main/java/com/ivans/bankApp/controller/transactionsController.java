package com.ivans.bankApp.controller;

import com.ivans.bankApp.entity.transactionDetails;
import com.ivans.bankApp.entity.userDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ivans.bankApp.repository.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("http://localhost:8081/")
public class transactionsController {

    @Autowired
    private usersRepository usersRepository;

    @Autowired
    private transactionsRepository transactionsRepository;

    @PostMapping("/getTransactions")
    public List<transactionDetails> getTransactionsById(@RequestBody transactionDetails request) {
        return this.transactionsRepository.findByUserId(request.getUserId());
    }

}