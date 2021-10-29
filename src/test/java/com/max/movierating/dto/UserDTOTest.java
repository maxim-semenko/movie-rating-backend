package com.max.movierating.dto;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserDTOTest {

    private User user1 = new User();

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .id(1L)
                .firstname("first1")
                .lastname("last1")
                .username("username1")
                .password("12345678")
                .email("email1")
                .roles(Set.of(new Role("ROLE_USER")))
                .basket(new Basket())
                .build();
    }

//    @Test
//    void toUser() {
//
//    }

    @Test
    void fromUser() {
        UserDTO expectedUserDTO = UserDTO.builder()
                .id(1L)
                .firstname("first1")
                .lastname("last1")
                .username("username1")
                .email("email1")
                .roles(Set.of(new Role("ROLE_USER")))
                .basket(new Basket())
                .build();

        UserDTO actualUserDTO = UserDTO.fromUser(user1);
        Assert.assertEquals(expectedUserDTO.toString(), actualUserDTO.toString());
    }
//
//    @Test
//    void fromListUser() {
//    }
}