package com.backend.Ecommerce.Backend.service.catogory;

import java.util.List;

import com.backend.Ecommerce.Backend.model.Category;



public interface ICategoryService {

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id); 



}
