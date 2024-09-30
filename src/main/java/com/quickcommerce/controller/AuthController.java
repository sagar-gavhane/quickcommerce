package com.quickcommerce.controller;

import com.quickcommerce.dto.ResponseDto;
import com.quickcommerce.dto.UserDto;
import com.quickcommerce.service.AuthService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<ResponseDto<Map<String, String>>> signIn(@Valid @RequestBody UserDto userDto) {
        String token = authService.signIn(userDto);

        Map<String, String> data = new HashMap<>();
        data.put("authToken", token);

        ResponseDto<Map<String, String>> responseDto = new ResponseDto<>("User signed-in successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/signOut")
    public ResponseEntity<ResponseDto> signOut(HttpServletRequest request, HttpServletResponse response) {
        authService.signOut(request, response);
        ResponseDto responseDto = new ResponseDto<>("User sign-out successfully.", null);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto> signUp(@Valid @RequestBody UserDto userDto) {
        UserDto signedUser = authService.signUp(userDto);
        Map<String, Object> data = new HashMap<>();
        data.put("user", signedUser);
        ResponseDto responseDto = new ResponseDto<>("User signed-up successfully.", data);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/signUpWithRole")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<ResponseDto> signUpWithRole(@Valid @RequestBody UserDto userDto) {
        UserDto signUpWithRole = authService.signUpWithRole(userDto);
        return null;
    }
}
