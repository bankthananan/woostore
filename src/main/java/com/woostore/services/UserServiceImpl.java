package com.woostore.services;

import com.woostore.dao.UserDao;
import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        Hibernate.initialize(user.getUserAuth());
        Hibernate.initialize(user.getAuthorities());
        return user;
    }
}
