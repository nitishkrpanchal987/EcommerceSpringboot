package com.practice.ecommercePrac.service.order;

import java.util.List;

import com.practice.ecommercePrac.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);
    List<Order> getUserOrders(Long userId);
}
