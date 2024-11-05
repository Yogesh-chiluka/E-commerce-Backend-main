package com.backend.Ecommerce.Backend.service.catogory;

import java.util.Optional;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.Ecommerce.Backend.exception.AlreadyExistsException;
import com.backend.Ecommerce.Backend.exception.CategoryNotFoundException;
import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.Category;
import com.backend.Ecommerce.Backend.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id){

        return categoryRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name){
        
        return Optional.ofNullable(categoryRepository.findByName(name))
        .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public List<Category> getAllCategories(){

        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category){
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
            .map(categoryRepository :: save)
            .orElseThrow(()-> new AlreadyExistsException(category.getName() + "already exists"));
    }   

    @Override
    public Category updateCategory(Category category, Long id){

        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());

            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public void deleteCategoryById(Long id){

        categoryRepository.findById(id)
            .ifPresentOrElse(categoryRepository:: delete, () -> {
                throw new CategoryNotFoundException("Category not found!"); 
            });
    }



    
}
