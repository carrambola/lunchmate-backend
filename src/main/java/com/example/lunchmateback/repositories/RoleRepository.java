package com.example.lunchmateback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lunchmateback.models.ERole;
import com.example.lunchmateback.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name); 
}
