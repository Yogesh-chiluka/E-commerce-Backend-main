package com.backend.Ecommerce.Backend.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.model.User;
import com.backend.Ecommerce.Backend.response.ApiResponse;
import com.backend.Ecommerce.Backend.service.cart.ICartItemService;
import com.backend.Ecommerce.Backend.service.cart.ICartService;
import com.backend.Ecommerce.Backend.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService; 
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long userId,
                                                    @RequestParam Long productId,
                                                    @RequestParam Integer quantity){

        try {

            User user = userService.getUserById(userId);
            Cart cart = cartService.initializerNewCart(user);
            cartItemService.addItemToCart(cart.getId(), productId, quantity);
    
            return ResponseEntity.ok(new ApiResponse("Item added successfully to cart", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,@PathVariable Long itemId){

        try {
            cartItemService.removeItemFromCart(cartId, itemId);
    
            return ResponseEntity.ok(new ApiResponse("Item removed from cart", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,@PathVariable Long itemId,@RequestParam Integer quantity){
        
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
    
            return ResponseEntity.ok(new ApiResponse("Item success fully updated", null));
            
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    
}
