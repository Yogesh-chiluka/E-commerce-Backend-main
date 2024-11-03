package com.backend.Ecommerce.Backend.controller;


import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.backend.Ecommerce.Backend.dto.OrderDto;
import com.backend.Ecommerce.Backend.model.Order;
import com.backend.Ecommerce.Backend.response.ApiResponse;
import com.backend.Ecommerce.Backend.service.order.IOrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {

    private final  IOrderService orderService;


    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId){

        try {
            Order order = orderService.placeOrder(userId);
            OrderDto orderDto = orderService.convertToDto(order);
            return ResponseEntity.ok(new ApiResponse("Item order Success!", orderDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error Occured!", e.getMessage()));
        }
    }
    
    @GetMapping("/{orderId}/order")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId){
        try {
            OrderDto order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Item order Success!", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
    }
    }

    @GetMapping("/user/{userId}/order")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId){
        try {
            List<OrderDto> order = orderService.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("Item order Success!", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Oops!", e.getMessage()));
        }
    }
}