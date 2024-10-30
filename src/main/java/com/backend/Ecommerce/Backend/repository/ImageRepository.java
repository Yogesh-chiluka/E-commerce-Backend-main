package com.backend.Ecommerce.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Ecommerce.Backend.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
     List<Image> findByProductId(Long productId);
     

}
