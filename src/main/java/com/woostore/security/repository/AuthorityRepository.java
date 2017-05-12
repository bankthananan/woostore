package com.woostore.security.repository;

import com.woostore.entity.security.Authority;
import com.woostore.entity.security.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dto on 17-Apr-17.
 */
public interface AuthorityRepository extends JpaRepository <Authority,Long> {
    Authority findByName(AuthorityName input);
}
