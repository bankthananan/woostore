package com.woostore.repository;

import com.woostore.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
    User findByUserAuthUsername(String username);
    List<User> findByFirstNameIgnoreCaseContainingOrUserAuthUsernameIgnoreCaseContaining(String firstname, String username);
}
