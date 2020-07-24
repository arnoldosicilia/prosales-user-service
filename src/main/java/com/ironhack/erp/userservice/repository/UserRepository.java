package com.ironhack.erp.userservice.repository;


import com.ironhack.erp.userservice.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
