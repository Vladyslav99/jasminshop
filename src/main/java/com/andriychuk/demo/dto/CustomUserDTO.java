package com.andriychuk.demo.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDTO {

    private String login;

    private String password;

    private String shippingAddress;

    private String fullName;

    private String phoneNumber;

    private Float discount;
}
