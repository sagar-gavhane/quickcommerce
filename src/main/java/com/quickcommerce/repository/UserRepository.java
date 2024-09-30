package com.quickcommerce.repository;

import com.quickcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = """
            SELECT
              CASE WHEN COUNT('*') = 0 THEN TRUE ELSE FALSE END
            FROM
              user
            WHERE
              email = :email OR mobile = :mobile
            """, nativeQuery = true)
    Long findByEmailOrMobile(@Param("email") String email, @Param("mobile") String mobile);
}
