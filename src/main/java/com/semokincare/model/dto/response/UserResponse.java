package com.semokincare.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String role;
    private Long createdAt;
    private Long updatedAt;
}
