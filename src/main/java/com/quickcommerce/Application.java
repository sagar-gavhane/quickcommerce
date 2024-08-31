package com.quickcommerce;

import com.quickcommerce.dto.UserDto;
import com.quickcommerce.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                map().setPassword(source.getPassword());
            }
        });

        // Map Entity to DTO (password is ignored due to @JsonIgnore)
        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                // No need to explicitly exclude password here
                // @JsonIgnore in DTO handles it
                map().setPassword(null);
            }
        });

        return modelMapper;
    }
}
