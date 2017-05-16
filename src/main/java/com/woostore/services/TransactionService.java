package com.woostore.services;

import com.woostore.entity.commerce.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactions(Date date);
    List<Transaction> getTransactionsPending(Date date);
    List<Transaction> getTransactionsPaid(Date date);
    Transaction findById(long id);
    List<Transaction> findAllByOwnerId(long id);
}
