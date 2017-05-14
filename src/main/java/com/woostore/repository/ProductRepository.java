package com.woostore.repository;

import com.woostore.entity.commerce.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findByDescriptionIgnoreCaseContaining(String description);
    List<Product> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name, String description);
    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(double firstPrice, double secondPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String name, double firstPrice, double secondPrice);
    List<Product> findByDescriptionIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String description, double firstPrice, double secondPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqualOrDescriptionIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String name, double firstPrice1, double secondPrice1, String description, double firstPrice2, double secondPrice2);
}
