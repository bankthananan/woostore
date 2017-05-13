package com.woostore.dao;

import com.woostore.entity.commerce.Transaction;
import com.woostore.repository.TransactionRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getTransactions() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
