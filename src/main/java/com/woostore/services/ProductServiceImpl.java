package com.woostore.services;

import com.woostore.dao.ProductDao;
import com.woostore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "image")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    String urlPath;

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public List<Product> getProducts() {
        List<Product> products = productDao.getProducts();
        for(Product product : products) {
            product.setPicture(urlPath + product.getPicture());
        }
        return products;
    }

    public Product findById(long id) {
        Product product = productDao.findById(id);
        if(product != null) {
            product.setPicture(urlPath + product.getPicture());
        }
        return product;
    }

    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }
}
