package com.semokincare.model.dto.request;

import com.semokincare.model.entity.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String brandId;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private String categoryProduct;
}
