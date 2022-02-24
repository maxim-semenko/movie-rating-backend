package com.max.movierating.dto.entity;

import com.max.movierating.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestRoleDTO {

    @NotBlank(message = "Name may not be empty")
    private String name;

    public Role toRole() {
        return Role.builder().name(name).build();
    }

}
