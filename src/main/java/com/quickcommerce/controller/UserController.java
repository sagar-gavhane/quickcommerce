package com.quickcommerce.controller;

import com.quickcommerce.dto.UserDto;
import com.quickcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(Principal principal) {
        UserDto user = userService.getSignedInUser(principal.getName());

        return ResponseEntity.ok(user);
    }
}
