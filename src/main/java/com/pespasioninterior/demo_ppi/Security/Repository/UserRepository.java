package com.pespasioninterior.demo_ppi.Security.Repository;

import com.pespasioninterior.demo_ppi.Security.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
