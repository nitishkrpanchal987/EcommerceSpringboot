package com.practice.ecommercePrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommercePrac.model.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Long> {

    void deleteAllByCartId(Long id);
    
}
