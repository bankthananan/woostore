package com.woostore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.woostore.config.json.View;
import com.woostore.entity.User;
import com.woostore.entity.security.AuthorityName;
import com.woostore.security.repository.AuthorityRepository;
import com.woostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @JsonView(View.Auth.class)
    @PostMapping("/user")
    public User addCusotmer(@RequestBody User user) {
        return userService.addCustomer(user);
    }

    @JsonView(View.Auth.class)
    @PostMapping("/user/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public User addAdmin(@RequestBody User user) {
        return userService.addAdmin(user);
    }

}
