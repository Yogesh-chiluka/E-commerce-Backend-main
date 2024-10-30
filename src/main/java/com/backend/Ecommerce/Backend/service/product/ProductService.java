package com.backend.Ecommerce.Backend.service.product;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.backend.Ecommerce.Backend.repository.ProductRepository;
import com.backend.Ecommerce.Backend.request.AddProductRequest;
import com.backend.Ecommerce.Backend.request.ProductUpdateRequest;
import com.backend.Ecommerce.Backend.service.dto.ImageDto;
import com.backend.Ecommerce.Backend.service.dto.ProductDto;
import com.backend.Ecommerce.Backend.repository.CategoryRepository;
import com.backend.Ecommerce.Backend.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

import com.backend.Ecommerce.Backend.exception.ProductNotFoundException;
import com.backend.Ecommerce.Backend.model.Category;
import com.backend.Ecommerce.Backend.model.Image;
import com.backend.Ecommerce.Backend.model.Product;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;


    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the category is found in the DB
        // If Yes, set it as the new product category
        // If No, the save it as a new category
        // The set as the new product category.

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }


    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id){
       productRepository.findById(id).ifPresentOrElse(productRepository::delete, 
       () ->{ throw new ProductNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId){

       return productRepository.findById(productId)
        .map(existingProduct -> updateExistingProduct(existingProduct, request))
        .map(productRepository :: save)
        .orElseThrow(()-> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category){

        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand){

        return productRepository.findByBrand(brand);
    }
    

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand){

    return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name){

        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String category, String name){

        return productRepository.findByBrandAndName(category, name);
    }


    @Override
    public Long countProductsByBrandAndName(String brand, String name){

        return productRepository.countByBrandAndName(brand, name);
    }   

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product){

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        List<Image> images = imageRepository.findByProductId(product.getId());

        List<ImageDto> imageDtos = images.stream()
            .map(image -> modelMapper.map(image, ImageDto.class))
            .toList();

        productDto.setImages(imageDtos);
        return productDto;


    }

}