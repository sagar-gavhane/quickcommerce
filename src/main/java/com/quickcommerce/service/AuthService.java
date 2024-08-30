package com.quickcommerce.service;

import com.quickcommerce.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String signIn(UserDto userDto);

    public UserDto signUp(UserDto userDto);

    void signOut(HttpServletRequest request, HttpServletResponse response);
}
