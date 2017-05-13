package com.woostore.controller;

import com.woostore.dao.ProductDao;
import com.woostore.dao.UserDao;
import com.woostore.entity.commerce.OrderItem;
import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.Transaction;
import com.woostore.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("transaction")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @PostMapping("transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }
}
