package com.quickcommerce.service;

import com.quickcommerce.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public User signUp(User user);
}
