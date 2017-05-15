package com.woostore.dao;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

import java.util.List;

public interface UserDao {
    User add(User user);
    User findById(long id);
    User findByUsername(String username);
    void delete(long id);
    List<User> getAll();
    List<User> searchUser(String text);
}
