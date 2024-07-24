package com.semokincare.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
}
