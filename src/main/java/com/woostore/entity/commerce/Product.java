package com.woostore.entity.commerce;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    double price;
    int amount;
    String description;
    String picture;
    double rating;
}
