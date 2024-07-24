package com.semokincare.service;

import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);
    AppUser loadUserById(String userId);

    UserResponse getUserById(AppUser request);
}
