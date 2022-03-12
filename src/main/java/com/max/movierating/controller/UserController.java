package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.other.RequestDeleteAccountDTO;
import com.max.movierating.dto.other.UpdatePasswordDTO;
import com.max.movierating.dto.other.UpdateUserIsNonLockedDTO;
import com.max.movierating.dto.other.UpdateUserRolesDTO;
import com.max.movierating.dto.other.UserDTO;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * REST controller for users requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.USERS_API)
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Method that finds all users by pageable.
     *
     * @param pageable contain some parameters (page, size, etc)
     * @return needed users
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(UserDTO.fromListUser(userService.findAll(), pageable), HttpStatus.OK);
    }

    /**
     * Method that finds user by id.
     *
     * @param id user's id
     * @return needed user
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(UserDTO.fromUser(userService.findById(id)), HttpStatus.OK);
    }

    /**
     * Method that finds user by username.
     *
     * @param username user's username
     * @return needed user
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(UserDTO.fromUser(userService.getByUsername(username)), HttpStatus.OK);
    }

    /**
     * Method that updates user by id.
     *
     * @param id      user's id
     * @param userDTO contain parameters for updating
     * @return updated user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDTO.toUser(), id), HttpStatus.OK);
    }

    /**
     * Method that delete user by ud.
     *
     * @param id      user's id
     * @param request contain password foy deleting
     * @return true of false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public ResponseEntity<Boolean> delete(@PathVariable Long id, @RequestBody RequestDeleteAccountDTO request) {
        return new ResponseEntity<>(userService.deleteAccount(id, request.getPassword()), HttpStatus.OK);
    }

    @PutMapping("/password/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordDTO request) {
        return new ResponseEntity<>(userService.updatePasswordById(id, request), HttpStatus.OK);
    }

    @PatchMapping("/{id}/locked")
    @PreAuthorize("hasRole('ADMIN') and #userId != authentication.principal.id")
    public ResponseEntity<User> updateLocked(@PathVariable Long id, @RequestBody UpdateUserIsNonLockedDTO request) {
        return new ResponseEntity<>(userService.updateUserIsNonLockedById(request.getIsNonLocked(), id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN') and #userId != authentication.principal.id")
    public ResponseEntity<User> updateRoles(@PathVariable Long id, @RequestBody UpdateUserRolesDTO request) {
        return new ResponseEntity<>(userService.updateUserRolesById(request.getRoles(), id), HttpStatus.OK);
    }

}
