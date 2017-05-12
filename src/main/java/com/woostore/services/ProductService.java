package com.woostore.services;


import com.woostore.entity.Product;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
}
