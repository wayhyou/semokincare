package com.semokincare.controller;

import com.semokincare.constant.APIUrl;
import com.semokincare.model.dto.CommonResponse;
import com.semokincare.model.dto.response.UserResponse;
import com.semokincare.repository.UserRepository;
import com.semokincare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.USER_API)
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserResponse>> getUser(@PathVariable String id) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.ok(
                CommonResponse.<UserResponse>builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message("user with id '" + id + "' found")
                        .data(user)
                        .build()
        );
    }

}
