package com.woostore.config;

import com.woostore.dao.ProductDao;
import com.woostore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements ApplicationRunner {

    @Autowired
    ProductDao productDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        productDao.addProduct(Product.builder().name("Nexus 5X").price(11000).amount(20).description("A phone").picture("5x.jpg").rating(4.35).build());
        productDao.addProduct(Product.builder().name("Nexus 6P").price(18000).amount(12).description("A phone").picture("6p.jpg").rating(4.28).build());
    }
}
