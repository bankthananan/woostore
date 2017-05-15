package com.woostore.dao;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;
import com.woostore.repository.UserRepository;
import com.woostore.security.repository.UserAuthRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserAuthUsername(username);
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findOne(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public List<User> searchUser(String text) {
        return userRepository.findByFirstNameIgnoreCaseContainingOrUserAuthUsernameIgnoreCaseContaining(text, text);
    }
}
