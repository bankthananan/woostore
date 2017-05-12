package com.woostore.dao;

import com.woostore.entity.Product;
import com.woostore.repository.ProductRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDBDao implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return Lists.newArrayList(productRepository.findAll());
    }

    public Product findById(long id) {
        return productRepository.findOne(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
