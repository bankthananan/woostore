package com.woostore.services;


import com.woostore.entity.commerce.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
    void deleteProduct(Long id);
}
