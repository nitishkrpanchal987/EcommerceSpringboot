package com.practice.ecommercePrac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.ecommercePrac.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    
}
