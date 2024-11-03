package com.backend.Ecommerce.Backend.service.cart;

import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.model.User;

import java.math.BigDecimal;


public interface ICartService {
    
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart initializerNewCart(User user);

    Cart getCartByUserId(Long userId);
}
