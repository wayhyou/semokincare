package com.semokincare.service.impl;

import com.semokincare.model.dto.auth.LoginRequest;
import com.semokincare.model.dto.auth.LoginResponse;
import com.semokincare.model.dto.request.BrandRequest;
import com.semokincare.model.dto.request.UserRequest;
import com.semokincare.model.dto.response.BrandResponse;
import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.model.entity.AppUser;
import com.semokincare.model.entity.Brand;
import com.semokincare.model.entity.User;
import com.semokincare.repository.BrandRepository;
import com.semokincare.repository.UserRepository;
import com.semokincare.security.JwtUtil;
import com.semokincare.service.AuthService;
import com.semokincare.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AuthServiceImpl implements AuthService {

    private final UserDetailService userDetailService;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse registerUser(UserRequest request, String role) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.saveAndFlush(
                User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .email(request.getEmail())
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .address(request.getAddress())
                        .role(role)
                        .build()
        );

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

    @Override
    public BrandResponse registerBrand(BrandRequest request) {
        UserResponse user = registerUser(request.getUser(), "BRAND");
        Brand brand = brandRepository.saveAndFlush(
                Brand.builder()
                        .user(User.builder()
                                .id(user.getId())
                                .build())
                        .name(request.getName())
                        .description(request.getDescription())
                        .logoUrl(request.getLogoUrl())
                        .build()
        );

        return BrandResponse.builder()
                .user(user)
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Authentication Manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        AppUser appUser = (AppUser) userDetailService.loadUserByUsername(userDetails.getUsername());

        // Generate Token
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .username(appUser.getUsername())
                .role(appUser.getRole())
                .token(token)
                .build();
    }
}
