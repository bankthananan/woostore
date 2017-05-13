package com.woostore.entity.commerce.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.woostore.entity.commerce.Transaction;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties(value = "true")
public class WooPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    long id;
//
//    @OneToOne
//    @NonNull
//    WooPaymentType wooPaymentType;

    @NonNull
    WooPaymentStatus wooPaymentStatus;

    @OneToOne
    @NonNull
    Transaction transaction;




}
