package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.RequestSendMessageDTO;
import com.max.movierating.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for mails requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.MAIL_API)
public class MailController {

    private final MailServiceImpl mailService;

    @Autowired
    public MailController(MailServiceImpl mailService) {
        this.mailService = mailService;
    }


    @PostMapping("")
    @PreAuthorize("hasRole('USER') and #request.userId == authentication.principal.id")
    public ResponseEntity<Boolean> sendMessage(@Valid @RequestBody RequestSendMessageDTO request) {
        return new ResponseEntity<>(mailService.performMessage(request), HttpStatus.OK);
    }

}
