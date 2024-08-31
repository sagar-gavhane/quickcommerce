package com.quickcommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Integer id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String mobile;
    private boolean isEmailVerified;
    private boolean isMobileVerified;
}
