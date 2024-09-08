package com.quickcommerce.repository;

import com.quickcommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findByIsActive(boolean isActive);

    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE sub_category AUTO_INCREMENT = 1")
    void resetAutoIncrement();
}
