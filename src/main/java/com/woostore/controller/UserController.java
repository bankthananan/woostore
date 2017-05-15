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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @JsonView(View.Auth.class)
    @PostMapping("/user")
    public User addCustomer(@RequestBody User user) {
        return userService.addCustomer(user);
    }

    @JsonView(View.Auth.class)
    @PostMapping("/user/staff")
    @PreAuthorize("hasRole('ADMIN')")
    public User addStaff(@RequestBody User user) {
        return userService.addStaff(user);
    }

    @JsonView(View.Auth.class)
    @PostMapping("/user/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public User addAdmin(@RequestBody User user) {
        return userService.addAdmin(user);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/search/{text}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> searchUser(@PathVariable("text") String text) {
        return userService.searchUser(text);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
