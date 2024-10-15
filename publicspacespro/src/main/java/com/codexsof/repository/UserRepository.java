package com.codexsof.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.codexsof.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
