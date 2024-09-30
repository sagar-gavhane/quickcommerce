package com.quickcommerce.service;

import com.quickcommerce.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String signIn(UserDto userDto);

    UserDto signUp(UserDto userDto);

    UserDto signUpWithRole(UserDto userDto);

    void signOut(HttpServletRequest request, HttpServletResponse response);
}
