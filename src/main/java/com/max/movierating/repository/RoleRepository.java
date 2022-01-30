package com.max.movierating.repository;

import com.max.movierating.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository for working with entity {@link Role}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
