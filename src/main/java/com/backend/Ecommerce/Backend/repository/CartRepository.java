package com.backend.Ecommerce.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Ecommerce.Backend.model.Cart;

public interface CartRepository  extends JpaRepository<Cart, Long> {
    
}
