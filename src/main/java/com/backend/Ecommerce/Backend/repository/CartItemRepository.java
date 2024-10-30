package com.backend.Ecommerce.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Ecommerce.Backend.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
