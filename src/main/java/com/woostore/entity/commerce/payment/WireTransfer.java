package com.woostore.entity.commerce.payment;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class WireTransfer implements WooPaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    long id;

    String fileName;

    @NonNull
    double amount;
}
