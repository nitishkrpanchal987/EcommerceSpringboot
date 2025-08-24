package com.practice.ecommercePrac.service.cart;

import com.practice.ecommercePrac.model.Cart;
import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();
}
