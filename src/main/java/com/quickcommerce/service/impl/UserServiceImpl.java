package com.quickcommerce.service.impl;

import com.quickcommerce.dto.UserDto;
import com.quickcommerce.entity.User;
import com.quickcommerce.repository.UserRepository;
import com.quickcommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getSignedInUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not able to fetch signed in user."));

        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }
}
