package com.max.movierating.configuration;

import com.max.movierating.entity.EnumRole;
import com.max.movierating.entity.Role;
import com.max.movierating.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!roleRepository.existsById(1L)) {
            roleRepository.save(new Role(EnumRole.ROLE_ADMIN));
        }
        if (!roleRepository.existsById(2L)) {
            roleRepository.save(new Role(EnumRole.ROLE_USER));
        }
    }
}
