package com.woostore.config;

import com.woostore.dao.ProductDao;
import com.woostore.dao.UserDao;
import com.woostore.entity.User;
import com.woostore.entity.commerce.Product;
import com.woostore.entity.security.Authority;
import com.woostore.entity.security.AuthorityName;
import com.woostore.entity.security.UserAuth;
import com.woostore.security.repository.AuthorityRepository;
import com.woostore.security.repository.UserAuthRepository;
import com.woostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Configuration
public class DataLoader implements ApplicationRunner {

    @Autowired
    ProductDao productDao;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserService userService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        productDao.addProduct(Product.builder().name("Nexus 5X").price(11000).amount(20).description("A phone").picture("5x.jpg").rating(4.35).build());
        productDao.addProduct(Product.builder().name("Nexus 6P").price(18000).amount(12).description("A phone").picture("6p.jpg").rating(4.28).build());



        Authority auth1 = Authority.builder().name(AuthorityName.ROLE_CUSTOMER).build();
        Authority auth2 = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);


        User user = User.builder().firstName("Zenon").lastName("SI").address("AAA").phoneNumber("001").build();
        user.setUserAuth(UserAuth.builder().username("zenon").password("zenon").build());

        userService.addAdmin(user);
    }
}
