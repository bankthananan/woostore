package com.woostore.dao;

import com.woostore.entity.commerce.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
}
