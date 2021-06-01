package com.andriychuk.demo.service;

import com.andriychuk.demo.dto.CustomUserDTO;
import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomUser saveAdmin(CustomUser customUser) {
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return customUserRepository.save(customUser);
    }

    public CustomUser saveCustomer(CustomUserDTO customUserDTO) {
        return customUserRepository.save(CustomUser.builder()
                .login(customUserDTO.getLogin())
                .password(passwordEncoder.encode(customUserDTO.getPassword()))
                .fullName(customUserDTO.getFullName())
                .phoneNumber(customUserDTO.getPhoneNumber())
                .shippingAddress(customUserDTO.getShippingAddress())
                .discount(customUserDTO.getDiscount())
                .role(CustomUser.Role.CUSTOMER)
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", username)));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(customUser.getRole().name()));
        return new org.springframework.security.core.userdetails.User(customUser.getLogin(),
                customUser.getPassword(), grantedAuthorities);
    }

    public CustomUser findByUserName(String name) {
        return customUserRepository.findByLogin(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", name)));
    }
}
