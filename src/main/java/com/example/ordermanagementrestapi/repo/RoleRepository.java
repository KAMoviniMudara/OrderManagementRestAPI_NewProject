package com.example.ordermanagementrestapi.repo;

import com.example.ordermanagementrestapi.entity.Role;
import com.example.ordermanagementrestapi.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);


}

