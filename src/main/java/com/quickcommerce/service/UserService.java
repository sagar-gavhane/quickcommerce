package com.quickcommerce.service;

import com.quickcommerce.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDto getSignedInUser(String email);

    public UserDto getUserById(Integer userId);
}
