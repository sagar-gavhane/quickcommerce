package com.quickcommerce.dto;

import com.quickcommerce.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    private Integer id;

    @NotNull
    private AddressType addressType = AddressType.HOME;

    @NotBlank
    private String line1;

    private String line2;

    @NotBlank
    private String city;

    private String landmark;

    @NotBlank
    private String contactPersonName;

    @Size(min = 10, max = 10)
    private String contactPersonMobile;

    @NotNull
    @Size(min = 6, max = 6)
    private String pinCode;

    private boolean isDefault;

    private UserDto user;
}
