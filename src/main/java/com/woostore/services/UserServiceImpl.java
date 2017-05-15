package com.woostore.services;

import com.woostore.dao.UserDao;
import com.woostore.entity.User;
import com.woostore.entity.security.AuthorityName;
import com.woostore.entity.security.UserAuth;
import com.woostore.security.repository.AuthorityRepository;
import com.woostore.security.repository.UserAuthRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        Hibernate.initialize(user.getUserAuth());
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

    @Override
    public User addAdminIn(User user) {
        user.getUserAuth().setAuthorities(new ArrayList<>());
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_CUSTOMER));
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_STAFF));
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_ADMIN));
        user.getUserAuth().setPassword(new BCryptPasswordEncoder().encode(user.getUserAuth().getPassword()));
        user.getUserAuth().setEnabled(true);
        user.getUserAuth().setLastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        user.setTransactions(new HashSet<>());
        userAuthRepository.save(user.getUserAuth());
        return userDao.add(user);
    }

    @Override
    public User addStaff(User user) {
        user.getUserAuth().setAuthorities(new ArrayList<>());
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_CUSTOMER));
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_STAFF));
        user.getUserAuth().setPassword(new BCryptPasswordEncoder().encode(user.getUserAuth().getPassword()));
        user.getUserAuth().setEnabled(true);
        user.getUserAuth().setLastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setTransactions(new HashSet<>());
        userAuthRepository.save(user.getUserAuth());
        return userDao.add(user);
    }

    @Override
    public User addAdmin(User user) {
        user.getUserAuth().setAuthorities(new ArrayList<>());
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_CUSTOMER));
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_STAFF));
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_ADMIN));
        user.getUserAuth().setPassword(new BCryptPasswordEncoder().encode(user.getUserAuth().getPassword()));
        user.getUserAuth().setEnabled(true);
        user.getUserAuth().setLastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setTransactions(new HashSet<>());
        userAuthRepository.save(user.getUserAuth());
        return userDao.add(user);
    }

    @Override
    public User addCustomer(User user) {
        user.getUserAuth().setAuthorities(new ArrayList<>());
        user.getUserAuth().getAuthorities().add(authorityRepository.findByName(AuthorityName.ROLE_CUSTOMER));
        user.getUserAuth().setPassword(new BCryptPasswordEncoder().encode(user.getUserAuth().getPassword()));
        user.getUserAuth().setEnabled(true);
        user.getUserAuth().setLastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setTransactions(new HashSet<>());
        userAuthRepository.save(user.getUserAuth());
        return userDao.add(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> searchUser(String text) {
        return userDao.searchUser(text);
    }
}
