package com.quickcommerce.repository;

import com.quickcommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, JpaRepository<Product, Integer> {
    List<Product> findByIsActive(boolean isActive, Pageable pageable);

//    Optional<Product> findById(Integer productId);

//    Product save(Product product);
}
