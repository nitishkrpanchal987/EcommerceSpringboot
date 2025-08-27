package com.practice.ecommercePrac.service.order;

import java.util.List;

import com.practice.ecommercePrac.dto.OrderDto;
import com.practice.ecommercePrac.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
