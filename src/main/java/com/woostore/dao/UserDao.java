package com.woostore.dao;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

public interface UserDao {
    User add(User user);
    User findById(long id);
    User findByUsername(String username);
}
