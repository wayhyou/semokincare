package com.semokincare.service;

import com.semokincare.model.dto.request.UserRequest;
import com.semokincare.model.dto.request.UserUpdateRequest;
import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.model.entity.AppUser;
import com.semokincare.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUserPut(UserUpdateRequest request);
    void deleteUser(String userId);
    UserResponse getUser(String userId);
    List<UserResponse> getAllUsers();
}
