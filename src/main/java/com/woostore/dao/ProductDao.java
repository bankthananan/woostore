package com.woostore.dao;

import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.SearchProductQuery;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
    void deleteProduct(Long id);
    List<Product> searchProduct(SearchProductQuery searchProductQuery);
}
