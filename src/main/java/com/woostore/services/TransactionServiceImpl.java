package com.woostore.services;

import com.woostore.dao.TransactionDao;
import com.woostore.entity.commerce.OrderItem;
import com.woostore.entity.commerce.Product;
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
    ProductService productService;

    @Autowired
    UserService userService;

    String urlPath;

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public List<Transaction> editPictureUrl(List<Transaction> transactions) {
        for(Transaction transaction : transactions) {
            for(OrderItem orderItem : transaction.getItems()) {
                orderItem.getProduct().setPicture(urlPath + orderItem.getProduct().getPicture());
            }
        }
        return transactions;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        for(OrderItem orderItem: transaction.getItems()) {
            orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
        }
        transaction.setDate(new Date());
        transaction.setOwner(userService.findById(transaction.getOwner().getId()));
        return transactionDao.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Date date) {
        return editPictureUrl(transactionDao.getTransactions(date));
    }

    @Override
    public List<Transaction> getTransactionsPending(Date date) {
        return editPictureUrl(transactionDao.getTransactionsPending(date));
    }

    @Override
    public List<Transaction> getTransactionsPaid(Date date) {
        return editPictureUrl(transactionDao.getTransactionsPaid(date));
    }

}
