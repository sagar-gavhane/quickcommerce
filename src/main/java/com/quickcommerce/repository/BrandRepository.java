package com.quickcommerce.repository;

import com.quickcommerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE brand AUTO_INCREMENT = 1")
    void resetAutoIncrement();
}
