package com.parcare.parcare_sistem.repository;

import com.parcare.parcare_sistem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);

    Optional<User> findOptionalByUsername(String username);
}
