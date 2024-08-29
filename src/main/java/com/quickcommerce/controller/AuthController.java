package com.quickcommerce.controller;

import com.quickcommerce.entity.User;
import com.quickcommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    private ResponseEntity<User> signUp(@RequestBody User user) {
        User signedUser = authService.signUp(user);

        return new ResponseEntity<>(signedUser, HttpStatus.CREATED);
    }
}
