package com.backend.Ecommerce.Backend.service.order;

import java.util.*;

import com.backend.Ecommerce.Backend.dto.OrderDto;
import com.backend.Ecommerce.Backend.model.Order;


public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);


}
