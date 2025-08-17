package com.practice.ecommercePrac.service.product;

import com.practice.ecommercePrac.model.Product;
import com.practice.ecommercePrac.request.ProductAddRequest;
import com.practice.ecommercePrac.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {


    Product addProduct(ProductAddRequest request);

    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
