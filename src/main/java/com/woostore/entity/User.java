package com.woostore.entity;

import com.fasterxml.jackson.annotation.*;
import com.woostore.config.json.View;
import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.security.Authority;
import com.woostore.entity.security.UserAuth;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "true")
public class User {

    @JsonView(View.Auth.class)
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @JsonView(View.Auth.class)
    @NonNull
    String firstName;

    @JsonView(View.Auth.class)
    @NonNull
    String lastName;

    @JsonView(View.Auth.class)
    @NonNull
    String address;

    @JsonView(View.Auth.class)
    @NonNull
    String phoneNumber;

    @JsonView(View.Auth.class)
    @NonNull
    @OneToOne
    UserAuth userAuth;

    @JsonIgnore
//    @JsonManagedReference(value = "abcd1")
//    @JsonBackReference
    @OneToMany(mappedBy = "owner")
    Set<Transaction> transactions;

    public List<Authority> getAuthorities(){
        return userAuth.getAuthorities();
    }

}
