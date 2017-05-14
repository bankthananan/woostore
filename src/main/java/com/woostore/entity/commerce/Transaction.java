package com.woostore.entity.commerce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.woostore.entity.User;
import com.woostore.entity.commerce.payment.WooPayment;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "true")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Cascade(CascadeType.ALL)
    @OneToMany
    Set<OrderItem> items;

    TransactionStatus status;

    @ManyToOne
    User owner;

    @OneToOne(mappedBy = "transaction")
    WooPayment wooPayment;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    public double getTotalPrice() {
        double price = 0;
        for(OrderItem item : this.items) {
            price += item.getProduct().getPrice();
        }
        return price;
    }

}
