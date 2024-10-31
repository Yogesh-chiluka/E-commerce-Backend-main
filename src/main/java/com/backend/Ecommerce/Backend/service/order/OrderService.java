package com.backend.Ecommerce.Backend.service.order;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.Ecommerce.Backend.dto.OrderDto;
import com.backend.Ecommerce.Backend.enums.OrderStatus;
import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Cart;
import com.backend.Ecommerce.Backend.model.Order;
import com.backend.Ecommerce.Backend.model.OrderItem;
import com.backend.Ecommerce.Backend.model.Product;
import com.backend.Ecommerce.Backend.repository.OrderRepository;
import com.backend.Ecommerce.Backend.repository.ProductRepository;
import com.backend.Ecommerce.Backend.service.cart.CartService;

import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Order placeOrder(Long userId){
        Cart cart = cartService.getCartByUserId(userId);
        Order order = creatOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);

        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        
        return savOrder;
    }

    private Order creatOrder(Cart cart){
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());

        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){

        return  cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);

            return new OrderItem(
                order,
                product,
                cartItem.getQuantity(),
                cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList){

        return orderItemList
            .stream()
            .map(item -> item.getPrice()
                    .multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public OrderDto getOrder(Long orderId){
        return orderRepository.findById(orderId).map(this:: convertToDto).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<OrderDto> getUserOrders(Long userId){

        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this:: convertToDto).toList();
    }

    private OrderDto convertToDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }
}
