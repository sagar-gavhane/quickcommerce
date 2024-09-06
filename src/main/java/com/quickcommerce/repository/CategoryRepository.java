package com.quickcommerce.repository;

import com.quickcommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIsActive(boolean isActive);

    List<Category> findByIsFeatured(boolean isFeatured);

    @Modifying
    @Query(nativeQuery = true, value = "ALTER TABLE category AUTO_INCREMENT = 1")
    void resetAutoIncrement();
}
