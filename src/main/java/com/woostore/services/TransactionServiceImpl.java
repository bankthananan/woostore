package com.woostore.services;

import com.woostore.dao.ProductDao;
import com.woostore.dao.TransactionDao;
import com.woostore.entity.commerce.OrderItem;
import com.woostore.entity.commerce.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "image")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    UserService userService;

    String urlPath;

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public List<Transaction> editPictureUrlList(List<Transaction> transactions) {
        for(Transaction transaction : transactions) {
            this.editPictureUrl(transaction);
        }
        return transactions;
    }

    public Transaction editPictureUrl(Transaction transaction) {
        for(OrderItem orderItem : transaction.getItems()) {
            orderItem.getProduct().setPicture(urlPath + "product/image/" + orderItem.getProduct().getPicture());
        }
        if(transaction.getWooPayment() != null) {
            transaction.getWooPayment().setFileName(urlPath + "transaction/payment/image/" + transaction.getWooPayment().getFileName());
        }
        return transaction;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        for(OrderItem orderItem: transaction.getItems()) {
            orderItem.setProduct(productDao.findById(orderItem.getProduct().getId()));
        }
        transaction.setDate(new Date());
        transaction.setOwner(userService.findById(transaction.getOwner().getId()));
        return transactionDao.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Date date) {
        return editPictureUrlList(transactionDao.getTransactions(date));
    }

    @Override
    public List<Transaction> getTransactionsPending(Date date) {
        return editPictureUrlList(transactionDao.getTransactionsPending(date));
    }

    @Override
    public List<Transaction> getTransactionsPaid(Date date) {
        return editPictureUrlList(transactionDao.getTransactionsPaid(date));
    }

    @Override
    public Transaction findById(long id) {
        return editPictureUrl(transactionDao.findById(id));
    }
}
