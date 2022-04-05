package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.dto.other.RequestLoginDTO;
import com.max.movierating.dto.other.RequestRestorePasswordDTO;
import com.max.movierating.dto.other.UserDTO;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.User;
import com.max.movierating.entity.enums.MessageTypeEnum;
import com.max.movierating.entity.enums.RoleEnum;
import com.max.movierating.entity.mail.MailCode;
import com.max.movierating.entity.mail.MailTypeMessage;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.AuthService;
import com.max.movierating.service.impl.mail.MailTypeMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Auth Service implementation that realize AuthService interface {@link AuthService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailTypeMessageServiceImpl mailTypeMessageService;
    private final MailCodeRepository mailCodeRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           AuthenticationManager authenticationManager,
                           UserServiceImpl userService,
                           JwtTokenProvider jwtTokenProvider,
                           BCryptPasswordEncoder passwordEncoder,
                           MailTypeMessageServiceImpl mailTypeMessageService,
                           MailCodeRepository mailCodeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.mailTypeMessageService = mailTypeMessageService;
        this.mailCodeRepository = mailCodeRepository;
    }

    /**
     * Method that register user in system.
     *
     * @param user for register
     * @return saved user
     */
    @Override
    @Transactional(rollbackFor = ConstraintViolationException.class)
    public User register(User user) {
        userService.existByUsername(user.getUsername());
        userService.existByEmail(user.getEmail());

        user.setRoles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER.toString())));
        user.setIsAccountNonLocked(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Basket basket = new Basket();
        PurchaseStorage purchaseStorage = new PurchaseStorage();

        user.setBasket(basket);
        user.setPurchaseStorage(purchaseStorage);

        basket.setUser(user);
        purchaseStorage.setUser(user);

        return userRepository.save(user);
    }

    /**
     * Method that login user in system.
     *
     * @param loginDTO contain username and password {@link RequestLoginDTO}
     * @return user date and token
     */
    @Override
    public Map<String, Object> login(RequestLoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getIsAccountNonLocked()) {
                try {
                    log.info("try user login!");
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));

                    Map<String, Object> response = new HashMap<>();
                    response.put("user", UserDTO.fromUser(user));
                    response.put("token", jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
                    log.info("User login is successful!");

                    return response;
                } catch (AuthenticationException e) {
                    log.error(ErrorConstant.ERROR_INVALID_PASSWORD);
                    throw new BadCredentialsException(ErrorConstant.ERROR_INVALID_PASSWORD);
                }
            } else {
                log.error("User is locked!");
                throw new BadRequestException("User is locked!");
            }
        } else {
            log.error("User was not found!");
            throw new ResourceNotFoundException("User was not found!");
        }
    }

    /**
     * Method that logout user from system.
     *
     * @return true
     */
    @Override
    public Boolean logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        log.info("User logout is successful");

        return true;
    }

    /**
     * Method that restore user's password.
     *
     * @return true
     */
    @Override
    public Boolean restorePassword(RequestRestorePasswordDTO restorePasswordDTO) {
        User user = userService.findByEmail(restorePasswordDTO.getEmail());

        MailTypeMessage mailTypeMessage = mailTypeMessageService.findByName(MessageTypeEnum.RESTORE_PASSWORD.toString());
        Optional<MailCode> optionalMailCode = mailCodeRepository.getLastByUserAndType(user, mailTypeMessage);
        if (optionalMailCode.isPresent()) {
            MailCode mailCode = optionalMailCode.get();
            if (Boolean.TRUE.equals(mailCode.getIsValid())) {
                if (Objects.equals(mailCode.getCode(), restorePasswordDTO.getEmailCode())) {
                    user.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
                    userRepository.save(user);
                } else {
                    mailCode.setCountAttempts(mailCode.getCountAttempts() + 1);
                    if (mailCode.getCountAttempts().equals(5)) {
                        mailCode.setIsValid(false);
                    }

                    mailCodeRepository.save(mailCode);
                    throw new BadRequestException("mail code not equals. Try Again!");
                }
            } else {
                throw new BadRequestException("Mail code is invalid. Send message Again!");
            }
        }

        return true;
    }

}
