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

    private Date getStartDate(Date date) {
        Date startDate = (Date) date.clone();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        return startDate;
    }

    private Date getEndDate(Date date) {
        Date endDate = (Date) date.clone();
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        return endDate;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Date date) {
        if(date == null) {
            return Lists.newArrayList(transactionRepository.findAll());
        }

        return transactionRepository.findByDateAfterAndDateBefore(getStartDate(date), getEndDate(date));
    }

    @Override
    public List<Transaction> getTransactionsPending(Date date) {
        if(date == null) {
            return transactionRepository.findByStatus(TransactionStatus.PENDING);
        }
        return transactionRepository.findByStatusAndDateAfterAndDateBefore(TransactionStatus.PENDING, getStartDate(date), getEndDate(date));
    }

    @Override
    public List<Transaction> getTransactionsPaid(Date date) {
        if(date == null) {
            return transactionRepository.findByStatus(TransactionStatus.PAID);
        }
        return transactionRepository.findByStatusAndDateAfterAndDateBefore(TransactionStatus.PAID, getStartDate(date), getEndDate(date));
    }
}
