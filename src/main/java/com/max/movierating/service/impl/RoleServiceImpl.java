package com.max.movierating.service.impl;

import com.max.movierating.entity.Role;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements DefaultService<Role, Long> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with id: " + id + " was not found"));

    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role, Long id) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public Role deleteById(Long id) {
        Role role = findById(id);
        roleRepository.delete(role);
        return role;
    }
}
