package com.practice.ecommercePrac.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.ecommercePrac.dto.OrderDto;
import com.practice.ecommercePrac.enums.OrderStatus;
import com.practice.ecommercePrac.exceptions.ResourceNotFoundException;
import com.practice.ecommercePrac.model.Cart;
import com.practice.ecommercePrac.model.Order;
import com.practice.ecommercePrac.model.OrderItem;
import com.practice.ecommercePrac.model.Product;
import com.practice.ecommercePrac.repository.OrderRepository;
import com.practice.ecommercePrac.repository.ProductRepository;
import com.practice.ecommercePrac.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final ProductRepository productRepository;
    private final ICartService cartService;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemsList = createOrderitems(order, cart);
        order.setTotalAmount(calculateTotalAmount(orderItemsList));
        order.setOrderItems(new HashSet<>(orderItemsList));

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        return order;
    }

    private List<OrderItem> createOrderitems(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(order, product, cartItem.getQuantity(), cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemsList) {
        return orderItemsList.stream().map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId).map(this :: convertToDto).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this :: convertToDto).toList();
    }

    private OrderDto convertToDto(Order order){
        return mapper.map(order, OrderDto.class);
    }
}
