package com.woostore.repository;

import com.woostore.entity.commerce.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
