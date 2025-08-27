package com.practice.ecommercePrac.service.cart;

import com.practice.ecommercePrac.model.Cart;
import com.practice.ecommercePrac.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Cart getCartByUserId(Long userId);

    Cart initializeNewCart(User user);
}
