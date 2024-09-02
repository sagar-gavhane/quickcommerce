package com.quickcommerce.entity;

import com.quickcommerce.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType = AddressType.HOME;

    @Column(nullable = false)
    private String line1;

    @Column(nullable = true)
    private String line2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = true)
    private String landmark;

    @Column(nullable = false)
    private String contactPersonName;

    @Column(nullable = true, length = 10)
    private String contactPersonMobile;

    @Column(nullable = false, length = 6)
    private String pinCode;

    private boolean isDefault = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
