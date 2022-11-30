package com.example.springblog3;

import com.example.springblog3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JPA will configure the query.
    User findByUsername(String username);

}