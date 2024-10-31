package com.backend.Ecommerce.Backend.service.cart;

import com.backend.Ecommerce.Backend.model.Cart;

import java.math.BigDecimal;


public interface ICartService {
    
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Long initializerNewCart();

    Cart getCartByUserId(Long userId);
}
