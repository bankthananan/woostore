package com.woostore.entity.commerce.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.woostore.entity.commerce.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = "true")
public class WooPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    long id;

    WooPaymentType wooPaymentType;

    String fileName;

    String paypalPaymentID;


}
