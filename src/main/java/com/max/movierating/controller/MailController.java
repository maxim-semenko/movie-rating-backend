package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = APIConstant.MAIL_API)
public class MailController {

    private final MailServiceImpl mailService;

    @Autowired
    public MailController(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #userId == authentication.principal.id")
    public void sendMessage(@PathVariable Long userId) {
        mailService.sendMessage("", "", " ");
    }

}
