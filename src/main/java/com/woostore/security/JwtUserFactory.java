package com.woostore.security;


import com.woostore.entity.security.Authority;
import com.woostore.entity.security.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserAuth userAuth) {
        return new JwtUser(
                userAuth.getId(),
                userAuth.getUsername(),
                userAuth.getPassword(),
                mapToGrantedAuthorities(userAuth.getAuthorities()),
                userAuth.getEnabled(),
                userAuth.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
