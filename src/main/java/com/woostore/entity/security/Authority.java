package com.woostore.entity.security;

import com.woostore.config.json.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonView(View.Auth.class)
    private AuthorityName name;

//    @JsonBackReference
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<UserAuth> userAuths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

//    public List<UserAuth> getUserAuths() {
//        return userAuths;
//    }
//
//    public void setUserAuths(List<UserAuth> userAuths) {
//        this.userAuths = userAuths;
//    }
}