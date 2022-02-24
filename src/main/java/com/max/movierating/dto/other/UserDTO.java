package com.max.movierating.dto.other;

import com.max.movierating.entity.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO class for sending {@link User}> object to client.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean isAdmin;
    private Boolean isAccountNonLocked;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setIsAccountNonLocked(isAccountNonLocked);

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
                .isAdmin(user.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getName())))
                .isAccountNonLocked(user.getIsAccountNonLocked())
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