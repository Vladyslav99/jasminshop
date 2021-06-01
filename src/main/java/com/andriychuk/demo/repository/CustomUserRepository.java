package com.andriychuk.demo.repository;

import com.andriychuk.demo.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByLogin(String login);
}
