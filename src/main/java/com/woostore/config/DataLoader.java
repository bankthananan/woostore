package com.woostore.config;

import com.woostore.dao.ProductDao;
import com.woostore.dao.TransactionDao;
import com.woostore.dao.UserDao;
import com.woostore.entity.User;
import com.woostore.entity.commerce.OrderItem;
import com.woostore.entity.commerce.Product;
import com.woostore.entity.commerce.Transaction;
import com.woostore.entity.commerce.TransactionStatus;
import com.woostore.entity.commerce.payment.WooPayment;
import com.woostore.entity.commerce.payment.WooPaymentType;
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

        productDao.addProduct(Product.builder().name("Nexus 5X").price(1200).amount(20).description("A phone").picture("5x.jpg").rating(4.35).enabled(true).build());
        productDao.addProduct(Product.builder().name("Nexus 6P").price(8000).amount(12).description("A phone").picture("6p.jpg").rating(4.28).enabled(true).build());
        productDao.addProduct(Product.builder().name("IPHONE 7S").price(9000).amount(10).description("A phone").picture("7s.jpg").rating(5.08).enabled(true).build());


        Authority auth1 = Authority.builder().name(AuthorityName.ROLE_CUSTOMER).build();
        Authority auth2 = Authority.builder().name(AuthorityName.ROLE_STAFF).build();
        Authority auth3 = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);
        authorityRepository.save(auth3);


        User user1 = User.builder().firstName("Zenon").lastName("SI").address("AAA").phoneNumber("001").enabled(true).userAuth(UserAuth.builder().username("admin").password("admin").build()).build();
        User user2 = User.builder().firstName("Iammiind").lastName("PU").address("BBB").phoneNumber("002").enabled(true).userAuth(UserAuth.builder().username("staff").password("staff").build()).build();
        User user3 = User.builder().firstName("Mark").lastName("ki").address("CCC").phoneNumber("003").enabled(true).userAuth(UserAuth.builder().username("user").password("user").build()).build();


        Transaction transaction = Transaction.builder().owner(user2).date(new Date()).build();
        OrderItem orderItem = OrderItem.builder().product(productDao.findById(1)).quantity(3).build();
        Set<OrderItem> items = new HashSet<>();
        items.add(orderItem);
        transaction.setItems(items);
        transaction.setStatus(TransactionStatus.PENDING);

        Transaction transaction2 = Transaction.builder().owner(user2).date(new Date()).build();
        OrderItem orderItem2 = OrderItem.builder().product(productDao.findById(2)).quantity(6).build();
        Set<OrderItem> items2 = new HashSet<>();
        items2.add(orderItem2);
        transaction2.setItems(items2);
        transaction2.setStatus(TransactionStatus.PAID);

        Transaction transaction3 = Transaction.builder().owner(user3).date(new Date()).build();
        OrderItem orderItem3 = OrderItem.builder().product(productDao.findById(1)).quantity(5).build();
        Set<OrderItem> items3 = new HashSet<>();
        items3.add(orderItem3);
        transaction3.setItems(items3);
        transaction3.setStatus(TransactionStatus.PENDING);

        Transaction transaction4 = Transaction.builder().owner(user3).date(new Date()).build();
        OrderItem orderItem4 = OrderItem.builder().product(productDao.findById(3)).quantity(2).build();
        OrderItem orderItem5 = OrderItem.builder().product(productDao.findById(2)).quantity(1).build();
        Set<OrderItem> items4 = new HashSet<>();
        items4.add(orderItem4);
        items4.add(orderItem5);
        transaction4.setItems(items4);
        transaction4.setStatus(TransactionStatus.PAID);

        WooPayment wooPayment = new WooPayment();
        wooPayment.setPaypalPaymentID("fsgdsfdsf");
        wooPayment.setWooPaymentType(WooPaymentType.PAYPAL);

        WooPayment wooPayment2 = new WooPayment();
        wooPayment2.setFileName("bill.jpg");
        wooPayment2.setWooPaymentType(WooPaymentType.WIRE_TRANSFER);

        transaction2.setWooPayment(wooPayment);
        transaction4.setWooPayment(wooPayment2);

        // set bill with user
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transaction);
        transactions.add(transaction2);
        user2.setTransactions(transactions);

        // set bill with user
        Set<Transaction> transactions2 = new HashSet<>();
        transactions2.add(transaction);
        transactions2.add(transaction2);
        user3.setTransactions(transactions2);

        userService.addAdminIn(user1);
        userService.addStaffIn(user2);
        userService.addCustomerIn(user3);

        transactionDao.addTransaction(transaction);
        transactionDao.addTransaction(transaction2);
        transactionDao.addTransaction(transaction3);
        transactionDao.addTransaction(transaction4);

    }
}
