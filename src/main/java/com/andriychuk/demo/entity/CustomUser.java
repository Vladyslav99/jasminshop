package com.andriychuk.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOM_USERS")
public class CustomUser {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String password;

    private String shippingAddress;

    private String fullName;

    private String phoneNumber;

    private Float discount;

    @Enumerated(EnumType.STRING)
    private CustomUser.Role role;

    public enum Role {
        CUSTOMER,
        ADMIN
    }
}
