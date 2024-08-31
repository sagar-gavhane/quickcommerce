package com.quickcommerce.repository;

import com.quickcommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUserId(Integer userId);

    Integer countByUserId(Integer userId);
}
