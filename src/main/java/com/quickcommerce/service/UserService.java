package com.quickcommerce.service;

import com.quickcommerce.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User getSignedInUser(String email);
}
