package com.max.movierating.dto.other;

import com.max.movierating.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UpdateUserRolesDTO {
    private Set<Role> roles = new HashSet<>();
}
