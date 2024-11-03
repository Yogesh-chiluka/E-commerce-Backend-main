package com.backend.Ecommerce.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Ecommerce.Backend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
