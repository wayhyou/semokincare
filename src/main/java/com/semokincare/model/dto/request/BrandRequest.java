package com.semokincare.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {
    private UserRequest user;
    private String name;
    private String description;
    private String logoUrl;
}
