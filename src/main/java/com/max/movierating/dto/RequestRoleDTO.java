package com.max.movierating.dto;

import com.max.movierating.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RequestRoleDTO {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    public Role toRole() {
        return Role.builder().name(name).build();
    }
}
