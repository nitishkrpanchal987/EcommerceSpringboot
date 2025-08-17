package com.practice.ecommercePrac.repository;

import com.practice.ecommercePrac.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
