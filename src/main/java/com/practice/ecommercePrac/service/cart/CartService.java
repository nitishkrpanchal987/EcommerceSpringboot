package com.practice.ecommercePrac.service.cart;

import java.math.BigDecimal;

import com.practice.ecommercePrac.exceptions.ResourceNotFoundException;
import com.practice.ecommercePrac.model.Cart;
import com.practice.ecommercePrac.repository.CartItemRepository;
import com.practice.ecommercePrac.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        // TODO Auto-generated method stub
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cart does not exists"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

}