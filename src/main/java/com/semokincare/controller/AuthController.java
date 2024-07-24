package com.semokincare.controller;

import com.semokincare.constant.APIUrl;
import com.semokincare.model.dto.CommonResponse;
import com.semokincare.model.dto.auth.LoginRequest;
import com.semokincare.model.dto.auth.LoginResponse;
import com.semokincare.model.dto.request.BrandRequest;
import com.semokincare.model.dto.request.UserRequest;
import com.semokincare.model.dto.response.BrandResponse;
import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.service.AuthService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.AUTH_API)
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {
    private final AuthService authenticationService;
    private static final Logger logger = LogManager.getLogger(AuthService.class);

    @PostMapping("/customer/register")
    public ResponseEntity<CommonResponse<UserResponse>> registerCustomer(@RequestBody UserRequest request) {
        logger.info("Accessed Endpoint : " + APIUrl.AUTH_API + "/customer/register");
        UserResponse response = authenticationService.registerUser(request, "CUSTOMER");

        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully register new customer account")
                .data(response)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/brand/register")
    public ResponseEntity<CommonResponse<BrandResponse>> registerBrand(@RequestBody BrandRequest request) {
        logger.info("Accessed Endpoint : " + APIUrl.AUTH_API + "/brand/register");
        BrandResponse response = authenticationService.registerBrand(request);

        CommonResponse<BrandResponse> commonResponse = CommonResponse.<BrandResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully register new brand account")
                .data(response)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> loginUser(@RequestBody LoginRequest request) {
        logger.info("Accessed Endpoint : " + APIUrl.AUTH_API + "/login");
        LoginResponse response = authenticationService.login(request);

        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success Login")
                .data(response)
                .build();
        return ResponseEntity.ok(commonResponse);
    }
}
