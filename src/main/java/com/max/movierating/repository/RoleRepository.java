package com.max.movierating.repository;

import com.max.movierating.entity.EnumRole;
import com.max.movierating.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(EnumRole name);
}
