package com.woostore.dao;

import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.SearchProductQuery;
import com.woostore.repository.ProductRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

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

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> searchProduct(SearchProductQuery searchProductQuery) {
        if(searchProductQuery.isPrice()) {
            if(searchProductQuery.isName() && searchProductQuery.isDescription()){
                return productRepository.findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqualOrDescriptionIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(searchProductQuery.getText(), searchProductQuery.getFirstPrice(), searchProductQuery.getSecondPrice(), searchProductQuery.getText(), searchProductQuery.getFirstPrice(), searchProductQuery.getSecondPrice());
            }
            if(searchProductQuery.isName()) {
                return productRepository.findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(searchProductQuery.getText(), searchProductQuery.getFirstPrice(), searchProductQuery.getSecondPrice());
            }
            if(searchProductQuery.isDescription()) {
                return productRepository.findByDescriptionIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(searchProductQuery.getText(), searchProductQuery.getFirstPrice(), searchProductQuery.getSecondPrice());
            }
            return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(searchProductQuery.getFirstPrice(), searchProductQuery.getSecondPrice());
        }
        else {
            if(searchProductQuery.isName() && searchProductQuery.isDescription()){
                return productRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchProductQuery.getText(), searchProductQuery.getText());
            }
            if(searchProductQuery.isName()) {
                return productRepository.findByNameIgnoreCaseContaining(searchProductQuery.getText());
            }
            if(searchProductQuery.isDescription()) {
                return productRepository.findByDescriptionIgnoreCaseContaining(searchProductQuery.getText());
            }
        }
        return Lists.newArrayList(productRepository.findAll());
    }
}
