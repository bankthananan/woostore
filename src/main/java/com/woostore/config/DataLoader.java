package com.woostore.config;

import com.woostore.dao.ProductDao;
import com.woostore.dao.TransactionDao;
import com.woostore.dao.UserDao;
import com.woostore.entity.User;
import com.woostore.entity.commerce.OrderItem;
import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.commerce.TransactionStatus;
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
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    TransactionDao transactionDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        productDao.addProduct(Product.builder().name("Nexus 5X").price(11000).amount(20).description("A phone").picture("5x.jpg").rating(4.35).enabled(true).build());
        productDao.addProduct(Product.builder().name("Nexus 6P").price(18000).amount(12).description("A phone").picture("6p.jpg").rating(4.28).enabled(true).build());



        Authority auth1 = Authority.builder().name(AuthorityName.ROLE_CUSTOMER).build();
        Authority auth2 = Authority.builder().name(AuthorityName.ROLE_STAFF).build();
        Authority auth3 = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);
        authorityRepository.save(auth3);


        User user = User.builder().firstName("Zenon").lastName("SI").address("AAA").phoneNumber("001").enabled(true).userAuth(UserAuth.builder().username("zenon").password("zenon").build()).build();


        Transaction transaction = Transaction.builder().owner(user).date(new Date()).build();
        OrderItem orderItem = OrderItem.builder().product(productDao.findById(1)).quantity(3).build();
        Set<OrderItem> items = new HashSet<>();
        items.add(orderItem);
        transaction.setItems(items);
        transaction.setStatus(TransactionStatus.PENDING);

        Transaction transaction2 = Transaction.builder().owner(user).date(new Date()).build();
        OrderItem orderItem2 = OrderItem.builder().product(productDao.findById(2)).quantity(6).build();
        Set<OrderItem> items2 = new HashSet<>();
        items2.add(orderItem2);
        transaction2.setItems(items2);
        transaction2.setStatus(TransactionStatus.PAID);


        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transaction);
        transactions.add(transaction2);
        user.setTransactions(transactions);

        userService.addAdminIn(user);
        transactionDao.addTransaction(transaction);
        transactionDao.addTransaction(transaction2);



    }
}
