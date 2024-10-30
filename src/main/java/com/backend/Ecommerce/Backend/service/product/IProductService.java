package com.backend.Ecommerce.Backend.service.product;

import java.util.List;

import com.backend.Ecommerce.Backend.model.Product;
import com.backend.Ecommerce.Backend.request.AddProductRequest;
import com.backend.Ecommerce.Backend.request.ProductUpdateRequest;
import com.backend.Ecommerce.Backend.service.dto.ProductDto;


public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getAllProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductsByBrandAndName(String category, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);

}
