package com.semokincare.repository;

import com.semokincare.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUsernameContainingIgnoreCase(String productName);
    Optional<User> findByUsername(String email);
}