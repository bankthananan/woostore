package com.woostore.services;

import com.woostore.entity.commerce.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
}
