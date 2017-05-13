package com.woostore.entity.commerce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.woostore.entity.User;
import com.woostore.entity.commerce.payment.WooPayment;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@JsonIgnoreProperties(value = "true")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

//    @OneToMany
//    @MapKeyClass(Product.class)
//    @NonNull
//    Map<Product, Integer> items;

    @ManyToOne
    @JsonBackReference
    User owner;

    @OneToOne(mappedBy = "transaction")
    WooPayment wooPayment;

}
