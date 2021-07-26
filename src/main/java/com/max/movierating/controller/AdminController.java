package com.max.movierating.controller;


import com.max.movierating.dto.UserDto;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping("users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);

//        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
