package com.semokincare.service;

import com.semokincare.model.dto.auth.LoginRequest;
import com.semokincare.model.dto.auth.LoginResponse;
import com.semokincare.model.dto.request.BrandRequest;
import com.semokincare.model.dto.request.UserRequest;
import com.semokincare.model.dto.response.BrandResponse;
import com.semokincare.model.dto.response.UserResponse;

public interface AuthService {
    UserResponse registerUser(UserRequest request, String role);
    BrandResponse registerBrand(BrandRequest request);
    LoginResponse login(LoginRequest request);
}
