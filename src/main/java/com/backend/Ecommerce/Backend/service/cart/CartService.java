package com.backend.Ecommerce.Backend.service.cart;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.model.User;
import com.backend.Ecommerce.Backend.repository.CartItemRepository;
import com.backend.Ecommerce.Backend.repository.CartRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id){

        Cart cart = cartRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id){
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id){
        Cart cart = getCart(id);

        return cart.getTotalAmount();
    }

    @Override
    public Cart initializerNewCart(User user){
        
        return Optional.ofNullable(getCartByUserId(user.getId()))
            .orElseGet(() -> {
                Cart cart = new Cart();
                cart.setUser(user);
                return cartRepository.save(cart);
            });
    }

    @Override
    public Cart getCartByUserId(Long userId){

        return cartRepository.findByUserId(userId);
    }
}
