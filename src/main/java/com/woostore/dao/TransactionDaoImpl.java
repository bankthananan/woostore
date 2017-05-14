package com.woostore.dao;

import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.commerce.TransactionStatus;
import com.woostore.repository.TransactionRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Date date) {
        if(date == null) {
            return Lists.newArrayList(transactionRepository.findAll());
        }
        return transactionRepository.findByDateEquals(date);
    }

    @Override
    public List<Transaction> getTransactionsPending(Date date) {
        if(date == null) {
            return transactionRepository.findByStatus(TransactionStatus.PENDING);
        }
        return transactionRepository.findByStatusAndDate(TransactionStatus.PENDING, date);
    }

    @Override
    public List<Transaction> getTransactionsPaid(Date date) {
        if(date == null) {
            return transactionRepository.findByStatus(TransactionStatus.PAID);
        }
        return transactionRepository.findByStatusAndDate(TransactionStatus.PAID, date);
    }
}
