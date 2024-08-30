package com.quickcommerce.service;

import com.quickcommerce.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String signIn(User user);

    public User signUp(User user);

    void signOut(HttpServletRequest request, HttpServletResponse response);
}
