package com.backend.Ecommerce.Backend.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.CartItem;
import com.backend.Ecommerce.Backend.response.ApiResponse;
import com.backend.Ecommerce.Backend.service.cart.ICartItemService;
import com.backend.Ecommerce.Backend.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class ICartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService; 

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                    @RequestParam Long productId,
                                                    @RequestParam Integer quantity){

        try {

            if(cartId == null){
                cartId = cartService.initializerNewCart();
            }
            cartItemService.addItemToCart(cartId, productId, quantity);
    
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

    @GetMapping("/getCartItem")
    public ResponseEntity<ApiResponse> getCartItem(Long cartId, Long productId){

        CartItem  cartItem = cartItemService.getCartItem(cartId, productId);

        return ResponseEntity.ok(new ApiResponse("Item successfully fetched from cart", cartItem));


    }

    
}
