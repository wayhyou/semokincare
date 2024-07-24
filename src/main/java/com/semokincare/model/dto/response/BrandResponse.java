package com.semokincare.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    private UserResponse user;
    private String id;
    private String name;
    private String description;
    private String logoUrl;
    private Long createdAt;
    private Long updatedAt;
}
