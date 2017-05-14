package com.woostore.repository;

import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.commerce.TransactionStatus;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByStatus(TransactionStatus status);
    List<Transaction> findByDateEquals(Date date);
    List<Transaction> findByStatusAndDate(TransactionStatus status, Date date);
}
