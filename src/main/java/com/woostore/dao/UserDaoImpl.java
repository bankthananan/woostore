package com.woostore.dao;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;
import com.woostore.repository.UserRepository;
import com.woostore.security.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserAuthUsername(username);
    }
}
