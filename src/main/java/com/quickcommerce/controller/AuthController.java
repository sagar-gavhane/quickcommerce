package com.quickcommerce.controller;

import com.quickcommerce.dto.UserDto;
import com.quickcommerce.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@Valid @RequestBody UserDto userDto) {
        String token = authService.signIn(userDto);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/signOut")
    public ResponseEntity<String> signOut(HttpServletRequest request, HttpServletResponse response) {
        authService.signOut(request, response);

        return ResponseEntity.ok("You have been logged out");
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto userDto) {
        UserDto signedUser = authService.signUp(userDto);

        return new ResponseEntity<>(signedUser, HttpStatus.CREATED);
    }
}
