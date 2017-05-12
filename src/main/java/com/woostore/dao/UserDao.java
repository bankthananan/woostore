package com.woostore.dao;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

public interface UserDao {
    void add(User user);
    User findByUsername(String username);
}
