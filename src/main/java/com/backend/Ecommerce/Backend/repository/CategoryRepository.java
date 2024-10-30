package com.backend.Ecommerce.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.Ecommerce.Backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existsByName(String name);

}