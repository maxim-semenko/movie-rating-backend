package com.max.movierating.dto;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * DTO class for sending user to client.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Integer balance;
    private Basket basket;
    private Set<Role> roles;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBalance(balance);
        user.setBasket(basket);
        user.setRoles(roles);

        return user;
    }

    /**
     * Method that convert from {@link User} to {@link UserDTO}.
     *
     * @param user {@link User} from database
     * @return {@link UserDTO} that will send to client
     */
    public static UserDTO fromUser(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .balance(user.getBalance())
                .basket(user.getBasket())
                .roles(user.getRoles())
                .build();
    }

    /**
     * Method that convert from {@link List<User>} to {@link List<UserDTO>}.
     *
     * @param users {@link List<User>} all users from database
     * @return {@link List<UserDTO>} that will send to client
     */
    public static List<UserDTO> fromListUser(final List<User> users) {
        List<UserDTO> listDto = new ArrayList<>();
        for (User user : users) {
            listDto.add(fromUser(user));
        }
        return listDto;
    }

}
