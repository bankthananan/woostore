package com.woostore.config.security;

import com.woostore.security.repository.AuthorityRepository;
import com.woostore.security.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Data {
    UserAuthRepository userSecurityRepository;
    @Autowired
    public void setUserSecurityRepository(UserAuthRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }
    AuthorityRepository authorityRepository;

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}
