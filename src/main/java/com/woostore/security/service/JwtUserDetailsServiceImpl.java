package com.woostore.security.service;


import com.woostore.entity.security.UserAuth;
import com.woostore.security.JwtUserFactory;
import com.woostore.security.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthRepository.findByUsername(username);

        if (userAuth == null) {
            throw new UsernameNotFoundException(String.format("No userAuth found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(userAuth);
        }
    }
}
