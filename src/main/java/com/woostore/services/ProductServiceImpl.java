package com.woostore.services;

import com.woostore.dao.ProductDao;
import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.SearchProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

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

    public List<Product> addUrlPicture(List<Product> products) {
        for (Product product : products) {
            product.setPicture(urlPath + product.getPicture());
        }
        return products;
    }

    public List<Product> getProducts() {
        return addUrlPicture(productDao.getProducts());
    }

    public Product findById(long id) {
        Product product = productDao.findById(id);
        if(product != null) {
            product.setPicture(urlPath + product.getPicture());
        }
        return product;
    }

    public Product addProduct(Product product) {
        product.setEnabled(true);
        Product returnProduct = productDao.addProduct(product);
        returnProduct.setPicture(urlPath + product.getPicture());
        return returnProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    @Override
    public List<Product> searchProduct(SearchProductQuery searchProductQuery) {
        return addUrlPicture(productDao.searchProduct(searchProductQuery));
    }
}
