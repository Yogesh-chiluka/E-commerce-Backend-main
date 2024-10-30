package com.backend.Ecommerce.Backend.service.cart;

import org.springframework.stereotype.Service;

import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.model.CartItem;
import com.backend.Ecommerce.Backend.model.Product;
import com.backend.Ecommerce.Backend.repository.CartItemRepository;
import com.backend.Ecommerce.Backend.repository.CartRepository;
import com.backend.Ecommerce.Backend.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    
    private final IProductService productService;
    private final CartRepository cartRepository;
    private final ICartService cartService;
    private final CartItemRepository cartItemRepository;


    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity){

        Cart cart = cartService.getCart(cartId);

        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElse(new CartItem());

        if(cartItem.getId() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }


    @Override
    public void removeItemFromCart(Long cartId, Long productId){

        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }


    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity){

        Cart cart = cartService.getCart(cartId);

        cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                item.setUnitPrice(item.getProduct().getPrice());
                item.setTotalPrice();
            });

    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId){

        Cart cart = cartService.getCart(productId);

        return cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        
    }
}
