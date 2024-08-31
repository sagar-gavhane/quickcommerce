package com.quickcommerce.service.impl;

import com.quickcommerce.dto.AddressDto;
import com.quickcommerce.entity.Address;
import com.quickcommerce.entity.User;
import com.quickcommerce.repository.AddressRepository;
import com.quickcommerce.repository.UserRepository;
import com.quickcommerce.service.AddressService;
import com.quickcommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AddressDto> getAddresses(Integer userId) {
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        List<AddressDto> addressDtos = new ArrayList<>();

        for (Address address : addresses) {
            addressDtos.add(modelMapper.map(address, AddressDto.class));
        }

        return addressDtos;
    }

    @Override
    public AddressDto addAddress(Integer userId, AddressDto addressDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Not able to fetch user with id " + userId));
        Integer count = addressRepository.countByUserId(userId);

        Address address = modelMapper.map(addressDto, Address.class);
        address.setUser(user);

        if (count == 0) {
            address.setDefault(true);
        }

        addressRepository.save(address);

        return modelMapper.map(address, AddressDto.class);
    }
}
