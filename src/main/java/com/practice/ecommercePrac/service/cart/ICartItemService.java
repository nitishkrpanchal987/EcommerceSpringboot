package com.practice.ecommercePrac.service.cart;

import com.practice.ecommercePrac.model.Cart;

public interface ICartItemService {
    void addItemToCart(Cart cart, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
