package com.woostore.repository;

import com.woostore.entity.commerce.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
