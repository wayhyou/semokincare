package com.semokincare.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {
    private String id;
    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;
    private String categoryProduct;
}
