package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Role;
import com.bsuir.cognispect.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(RoleEnum roleName);

    Optional<Role> findById(UUID id);
}
