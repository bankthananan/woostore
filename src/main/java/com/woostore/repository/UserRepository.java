package com.woostore.repository;

import com.woostore.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
    User findByUserAuthUsername(String username);
}
