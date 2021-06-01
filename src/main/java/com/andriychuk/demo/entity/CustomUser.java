package com.andriychuk.demo.entity;

import lombok.*;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private CustomUser.Role role;

    public enum Role {
        CUSTOMER,
        ADMIN
    }
}
