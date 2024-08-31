package com.quickcommerce.controller;

import com.quickcommerce.dto.AddressDto;
import com.quickcommerce.entity.MyUserDetails;
import com.quickcommerce.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    private ResponseEntity<List<AddressDto>> getAddressesOfSignedInUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<AddressDto> addressDtos = addressService.getAddresses(myUserDetails.getId());

        return ResponseEntity.ok(addressDtos);
    }

    @PostMapping
    private ResponseEntity<AddressDto> addAddress(@Valid @RequestBody AddressDto addressDto) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AddressDto response = addressService.addAddress(myUserDetails.getId(), addressDto);

        return ResponseEntity.ok(response);
    }
}
