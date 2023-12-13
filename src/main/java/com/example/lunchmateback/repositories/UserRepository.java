package com.example.lunchmateback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // select * from users where username='%username' limit 1;

    Boolean existsByUsername(String username); // select count(*) from users where usernmae=...

    Boolean existsByEmail(String email); // 
}
