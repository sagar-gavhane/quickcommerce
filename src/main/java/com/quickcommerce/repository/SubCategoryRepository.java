package com.quickcommerce.repository;

import com.quickcommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE sub_category AUTO_INCREMENT = 1")
    void resetAutoIncrement();
}
