package com.quickcommerce.service;

import com.quickcommerce.dto.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public List<AddressDto> getAddresses(Integer userId);

    public AddressDto addAddress(Integer userId, AddressDto addressDto);
}
