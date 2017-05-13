package com.woostore.services;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

public interface UserService {
    User findById(long id);
    User addCustomer(User user);
    User addAdmin(User user);
    User getUserByUsername(String username);
}
