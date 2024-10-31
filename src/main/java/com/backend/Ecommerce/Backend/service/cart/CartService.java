package com.backend.Ecommerce.Backend.service.cart;

import org.springframework.stereotype.Service;

import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.repository.CartItemRepository;
import com.backend.Ecommerce.Backend.repository.CartRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong();

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
    public Long initializerNewCart(){
        Cart newCart = new Cart();

        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId); 

        return cartRepository.save(newCart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId){

        return cartRepository.findByUserId(userId);
    }
}
