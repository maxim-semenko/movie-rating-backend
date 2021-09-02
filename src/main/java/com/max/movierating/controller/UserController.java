package com.max.movierating.controller;

import com.max.movierating.dto.RegisterRequestDTO;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(UserDTO.fromListUser(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(UserDTO.fromUser(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> save(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        return new ResponseEntity<>(userService.save(requestDTO.toUser()), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #userDTO.id == authentication.principal.id")
    public ResponseEntity<User> update(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDTO.toUser()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(UserDTO.fromUser(userService.getByUsername(username)), HttpStatus.OK);
    }

}