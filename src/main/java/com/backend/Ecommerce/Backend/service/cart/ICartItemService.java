package com.backend.Ecommerce.Backend.service.cart;

import com.backend.Ecommerce.Backend.model.CartItem;

public interface ICartItemService {
    
    void addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
