package com.semokincare.model.dto.response;

import com.semokincare.model.entity.Brand;
import com.semokincare.model.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String brandId;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private String categoryProduct;
    private Long createdAt;
    private Long updatedAt;
}
