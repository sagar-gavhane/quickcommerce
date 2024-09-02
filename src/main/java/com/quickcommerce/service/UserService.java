package com.quickcommerce.service;

import com.quickcommerce.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getSignedInUser(String email);

    UserDto getUserById(Integer userId);
}
