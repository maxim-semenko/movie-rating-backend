package com.max.movierating.dto;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import com.max.movierating.entity.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOTest {

    UserDTO userDTO = UserDTO.builder()
            .id(1L)
            .firstname("firstname")
            .lastname("lastname")
            .username("username")
            .email("email")
            .isAccountNonLocked(true)
            .isAdmin(true)
            .build();

    @Test
    void toUser() {
        User user = User.builder()
                .id(1L)
                .firstname("firstname")
                .lastname("lastname")
                .username("username")
                .email("email")
                .isAccountNonLocked(true)
                .build();

        assertEquals(user, userDTO.toUser());
    }

    @Test
    void fromUser() {
        User user = User.builder()
                .id(1L)
                .firstname("firstname")
                .lastname("lastname")
                .username("username")
                .email("email")
                .roles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString())))
                .isAccountNonLocked(true)
                .build();

        assertEquals(userDTO, UserDTO.fromUser(user));

    }

    @Test
    void fromListUser() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).firstname("firstname").lastname("lastname").username("username")
                .email("email").roles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString()))).isAccountNonLocked(true).build());

        users.add(User.builder().id(2L).firstname("firstname2").lastname("lastname2").username("username2")
                .email("email2").roles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString()))).isAccountNonLocked(true).build());

        Pageable pageable = PageRequest.of(0, 2);

        assertEquals(users.size(), UserDTO.fromListUser(users, pageable).getTotalElements());
    }

    @Test
    void fromListUserIfPageSizeMoreThanSizeList() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).firstname("firstname").lastname("lastname")
                .username("username").email("email")
                .roles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString())))
                .isAccountNonLocked(true).build());

        users.add(User.builder().id(2L).firstname("firstname2").lastname("lastname2")
                .username("username2").email("email2")
                .roles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString())))
                .isAccountNonLocked(true).build());

        Pageable pageable = PageRequest.of(0, 3);

        assertEquals(users.size(), UserDTO.fromListUser(users, pageable).getTotalElements());
    }

    @Test
    void getId() {
        assertEquals(1L, userDTO.getId());
    }

    @Test
    void getUsername() {
        assertEquals("username", userDTO.getUsername());
    }

    @Test
    void getFirstname() {
        assertEquals("firstname", userDTO.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("lastname", userDTO.getLastname());
    }

    @Test
    void getEmail() {
        assertEquals("email", userDTO.getEmail());
    }

    @Test
    void getIsAdmin() {
        assertEquals(true, userDTO.getIsAdmin());
    }

    @Test
    void getIsAccountNonLocked() {
        assertEquals(true, userDTO.getIsAccountNonLocked());
    }

    @Test
    void setId() {
        userDTO.setId(2L);
        assertEquals(2L, userDTO.getId());
    }

    @Test
    void setUsername() {
        userDTO.setUsername("username1");
        assertEquals("username1", userDTO.getUsername());
    }

    @Test
    void setFirstname() {
        userDTO.setFirstname("firstname1");
        assertEquals("firstname1", userDTO.getFirstname());
    }

    @Test
    void setLastname() {
        userDTO.setLastname("lastname1");
        assertEquals("lastname1", userDTO.getLastname());
    }

    @Test
    void setEmail() {
        userDTO.setEmail("email1");
        assertEquals("email1", userDTO.getEmail());
    }

    @Test
    void setIsAdmin() {
        userDTO.setIsAdmin(false);
        assertEquals(false, userDTO.getIsAdmin());
    }

    @Test
    void setIsAccountNonLocked() {
        userDTO.setIsAccountNonLocked(false);
        assertEquals(false, userDTO.getIsAccountNonLocked());
    }

}