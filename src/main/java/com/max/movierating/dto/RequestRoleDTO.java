package com.max.movierating.dto;

import com.max.movierating.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRoleDTO {

    private String name;

    public Role toRole() {
        return new Role(name);
    }
}
