package com.woostore.repository;

import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.commerce.TransactionStatus;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByOrderByIdDesc();
    List<Transaction> findByStatusOrderByIdDesc(TransactionStatus status);
    List<Transaction> findByDateAfterAndDateBeforeOrderByIdDesc(Date dateStart, Date dateEnd);
    List<Transaction> findByStatusAndDateAfterAndDateBeforeOrderByIdDesc(TransactionStatus status, Date dateStart, Date dateEnd);
    List<Transaction> findAllByOwnerIdOrderByIdDesc(long id);
}
