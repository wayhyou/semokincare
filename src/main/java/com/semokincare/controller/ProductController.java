package com.semokincare.controller;

import com.semokincare.constant.APIUrl;
import com.semokincare.model.dto.request.ProductRequest;
import com.semokincare.model.dto.request.ProductUpdateRequest;
import com.semokincare.model.dto.response.ProductResponse;
import com.semokincare.model.entity.Product;
import com.semokincare.response.ResponseHandler;
import com.semokincare.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.PRODUCT_API)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    // Read Specific Product Details from DB
    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductDetails(@PathVariable("productId") String productId) {
        return ResponseHandler.responseBuilder("Requested Product Details are given here bro.", HttpStatus.OK, productService.getProductById(productId));
    }

    // Read All Product Details from DB
    @GetMapping
    public List<ProductResponse> getAllProductDetails() {
        return productService.getAllProducts();
    }

    // Read All Product By Name
    @GetMapping("/{productName}")
    public List<ProductResponse> getProductByName(@PathVariable("productName") String productName) {
        return productService.getByProductName(productName);
    }

    @PostMapping
    public ResponseEntity<Object> createProductDetails(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseHandler.responseBuilder("Product Created Successfully", HttpStatus.OK, request);
    }

    @PutMapping
    public ResponseEntity<Object> updateProductDetails(@RequestBody ProductUpdateRequest request) {
        productService.updateProduct(request);
        return ResponseHandler.responseBuilder("Product Updated Successfully", HttpStatus.OK, request);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProductDetails(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return ResponseHandler.responseBuilder("Product Deleted Successfully", HttpStatus.OK, null);
    }
}
