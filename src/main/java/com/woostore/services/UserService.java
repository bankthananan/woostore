package com.woostore.services;

import com.woostore.entity.User;
import com.woostore.entity.security.UserAuth;

import java.util.List;

public interface UserService {
    User findById(long id);
    User addCustomer(User user);
    User addAdmin(User user);
    User addStaff(User user);
    User getUserByUsername(String username);
    User addAdminIn(User user);
    User addStaffIn(User user);
    User addCustomerIn(User user);
    void deleteUser(long id);
    List<User> getAll();
    List<User> searchUser(String text);
}
