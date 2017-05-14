package com.woostore.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.woostore.config.json.View;
import com.woostore.entity.User;
import com.woostore.entity.security.AuthorityName;
import com.woostore.repository.UserRepository;
import com.woostore.security.repository.AuthorityRepository;
import com.woostore.services.UserService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    /user for admin use only

    @JsonView(View.Auth.class)
    @PostMapping("/user")
    public User addCustomer(@RequestBody User user) {
        return userService.addCustomer(user);
    }

    @JsonView(View.Auth.class)
    @PostMapping("/user/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public User addAdmin(@RequestBody User user) {
        return userService.addAdmin(user);
    }

    @GetMapping("/user")
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

}
