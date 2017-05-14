package com.woostore.controller;

import com.woostore.entity.commerce.Transaction;
import com.woostore.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("transaction")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions(null);
    }

    @GetMapping("transaction/pending")
    public List<Transaction> getTransactionsPending() {
        return transactionService.getTransactionsPending(null);
    }

    @GetMapping("transaction/paid")
    public List<Transaction> getTransactionsPaid() {
        return transactionService.getTransactionsPaid(null);
    }

    @GetMapping("transaction/{timestamp}")
    public List<Transaction> getTransactions(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        System.out.println(date);
        return transactionService.getTransactions(date);
    }

    @GetMapping("transaction/pending/{timestamp}")
    public List<Transaction> getTransactionsPending(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return transactionService.getTransactionsPending(date);
    }

    @GetMapping("transaction/paid/{timestamp}")
    public List<Transaction> getTransactionsPaid(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return transactionService.getTransactionsPaid(date);
    }

    @PostMapping("transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }
}
