package com.semokincare.service.impl;

import com.semokincare.exception.ProductNotFoundException;
import com.semokincare.model.dto.request.ProductRequest;
import com.semokincare.model.dto.request.ProductUpdateRequest;
import com.semokincare.model.dto.response.ProductResponse;
import com.semokincare.model.entity.Brand;
import com.semokincare.model.entity.Product;
import com.semokincare.repository.BrandRepository;
import com.semokincare.repository.ProductRepository;
import com.semokincare.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId()).orElseThrow(() -> new ProductNotFoundException(request.getBrandId()));
        Product product = productRepository.saveAndFlush(
                Product.builder()
                        .brand(brand)
                        .name(request.getName())
                        .description(request.getDescription())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .imageUrl(request.getImageUrl())
                        .categoryProduct(Product.CategoryProduct.valueOf(request.getCategoryProduct()))
                        .build()
        );
        return convertToProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(ProductUpdateRequest request) {
        Product product = findByIdOrThrowNotFound(request.getId());
        return convertToProductResponse(productRepository.saveAndFlush(product));
    }

    @Override
    public void deleteProduct(String productId) {
        findByIdOrThrowNotFound(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse getProductById(String productId) {
        return convertToProductResponse(findByIdOrThrowNotFound(productId));
    }

    @Override
    public List<ProductResponse> getByProductName(String productName) {
        return productRepository.findByNameContainingIgnoreCase(productName).stream().map(this::convertToProductResponse).toList();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToProductResponse).toList();
    }

    public Product findByIdOrThrowNotFound(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product with id '" + id + "' not found"));
    }

    public ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .brandId(product.getBrand().getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .categoryProduct(product.getCategoryProduct().name())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
