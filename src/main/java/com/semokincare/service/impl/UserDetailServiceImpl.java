package com.semokincare.service.impl;

import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.model.entity.AppUser;
import com.semokincare.model.entity.User;
import com.semokincare.repository.UserRepository;
import com.semokincare.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid credential user"));
        // id,username, password, email, fullName, phoneNumber, address, role, createdAt, updatedAt
        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public AppUser loadUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Invalid credential user"));
        // id,username, password, email, fullName, phoneNumber, address, role, createdAt, updatedAt
        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public UserResponse getUserById(AppUser request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new UsernameNotFoundException("Invalid credential user"));
        // id,username, password, email, fullName, phoneNumber, address, role, createdAt, updatedAt
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
}
