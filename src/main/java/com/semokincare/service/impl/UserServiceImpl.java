package com.semokincare.service.impl;

import com.semokincare.model.dto.request.UserRequest;
import com.semokincare.model.dto.request.UserUpdateRequest;
import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.model.entity.User;
import com.semokincare.repository.UserRepository;
import com.semokincare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .role("CUSTOMER")
                .build();

        return convertToUserResponse(user);
    }

    @Override
    public UserResponse updateUserPut(UserUpdateRequest request) {
        findByIdOrThrowNotFound(request.getId());
        User user = userRepository.saveAndFlush(
                User.builder()
                        .id(request.getId())
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .address(request.getAddress())
                        .role(request.getRole())
                        .build()
        );
        return convertToUserResponse(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getUser(String userId) {
        return convertToUserResponse(findByIdOrThrowNotFound(userId));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToUserResponse).toList();
    }

    public UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User findByIdOrThrowNotFound(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id'" + id + "' not found"));
    }

}
