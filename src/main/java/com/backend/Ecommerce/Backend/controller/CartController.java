package com.backend.Ecommerce.Backend.controller;

import static org.springframework.http.HttpStatus.*;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;

import com.backend.Ecommerce.Backend.response.ApiResponse;
import com.backend.Ecommerce.Backend.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {
    
    private final ICartService cartService;


    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId){
   
        try {
            Cart cart = cartService.getCart(cartId);
    
            return ResponseEntity.ok(new ApiResponse("success", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("cart not found", null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId){

        try {
            cartService.clearCart(cartId);
    
            return ResponseEntity.ok(new ApiResponse("clear cart successfull", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long cartId){

            try {
                BigDecimal totalPrice = cartService.getTotalPrice(cartId);
        
                return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
    }
}
