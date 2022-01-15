package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for admin requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.ADMIN_API)
@Slf4j
public class AdminController {

    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/locked/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateAccountIsNonLocked(@RequestParam("status") Boolean status, @PathVariable Long userId) {
        return new ResponseEntity<>(adminService.updateUserIsNonLockedById(status, userId), HttpStatus.OK);
    }

    @PutMapping("/role/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUserRole(@PathVariable Long userId) {
        return new ResponseEntity<>(adminService.addOrRemoveAdminRoleById(userId), HttpStatus.OK);
    }

}
