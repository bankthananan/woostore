package com.woostore.services;


import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.SearchProductQuery;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
    void deleteProduct(Long id);
    List<Product> searchProduct(SearchProductQuery searchProductQuery);
}
