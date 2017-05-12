package com.woostore.services;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

public interface UserService {
    User getUserByUsername(String username);
}
