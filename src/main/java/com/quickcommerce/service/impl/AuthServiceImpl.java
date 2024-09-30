package com.quickcommerce.service.impl;

import com.quickcommerce.dto.UserDto;
import com.quickcommerce.entity.User;
import com.quickcommerce.repository.UserRepository;
import com.quickcommerce.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    @Transactional
    public String signIn(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authToken);

        if (!authenticated.isAuthenticated()) {
            throw new BadCredentialsException("Email or password doesn't match.");
        }

        return jwtService.generateToken(user.getEmail());
    }

    @Override
    @Transactional
    public UserDto signUp(UserDto userDto) {
        try {
            Long exists = userRepository.findByEmailOrMobile(userDto.getEmail(), userDto.getMobile());

            if (exists == 0) {
                throw new DataIntegrityViolationException("Email or mobile number already exists.");
            }

            // TODO: not safe to accept role in request body
            // TODO: only admin can be able to add all other role except CUSTOMER

            User user = modelMapper.map(userDto, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);

            return modelMapper.map(user, UserDto.class);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Email or mobile number already exists.");
        }
    }

    @Override
    public void signOut(HttpServletRequest request, HttpServletResponse response) {
        // FIXME: signOut method logout user from SecurityContextHolder but doesn't invalid jwt token that shared with user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    // create user with specific role
    @Override
    public UserDto signUpWithRole(UserDto userDto) {
        System.out.println("UserDto" + userDto);
        return null;
    }
}
