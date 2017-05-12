package com.woostore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.woostore.config.json.View;
import com.woostore.entity.security.Authority;
import com.woostore.entity.security.UserAuth;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {


    @Id
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
    @JsonManagedReference
    @OneToOne
    UserAuth userAuth;


    public List<Authority> getAuthorities(){
        return userAuth.getAuthorities();
    }

}
