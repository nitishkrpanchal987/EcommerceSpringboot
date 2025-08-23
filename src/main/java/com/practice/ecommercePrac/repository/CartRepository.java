package com.practice.ecommercePrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommercePrac.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
}
