package com.max.movierating.dto;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * DTO class for sending {@link User}> object to client.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Getter
@Setter
@Builder
@Slf4j
public class UserDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Basket basket;
    private Set<Role> roles;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBasket(basket);
        user.setRoles(roles);

        return user;
    }

    /**
     * Method that convert from {@link User} to {@link UserDTO}.
     *
     * @param user {@link User} from database
     * @return {@link UserDTO} object, that will send to client
     */
    public static UserDTO fromUser(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .basket(user.getBasket())
                .roles(user.getRoles())
                .build();
    }

    /**
     * Method that converts from {@link List<User>} to {@link List<UserDTO>}.
     *
     * @param users {@link List<User>} all users from database
     * @return {@link List<UserDTO>} collection, that will send to client
     */
    public static Page<UserDTO> fromListUser(final List<User> users, Pageable pageable) {
        List<UserDTO> listDto = new ArrayList<>();

        for (User user : users) {
            listDto.add(fromUser(user));
        }
        if (pageable.getPageSize() > listDto.size()) {
            return new PageImpl<>(listDto, pageable, listDto.size());
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listDto.size());

        return new PageImpl<>(listDto.subList(start, end), pageable, listDto.size());
    }

}