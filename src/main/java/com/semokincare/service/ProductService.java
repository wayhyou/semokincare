package com.semokincare.service;

import com.semokincare.model.dto.request.ProductRequest;
import com.semokincare.model.dto.request.ProductUpdateRequest;
import com.semokincare.model.dto.response.ProductResponse;
import com.semokincare.model.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(ProductUpdateRequest request);
    void deleteProduct(String productId);
    ProductResponse getProductById(String productId);
    List<ProductResponse> getByProductName(String productName);
    List<ProductResponse> getAllProducts();
}
