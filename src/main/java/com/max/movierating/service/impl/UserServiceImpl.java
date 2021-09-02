package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.EnumRole;
import com.max.movierating.entity.User;
import com.max.movierating.exception.FilmNotFoundException;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.exception.UserNotFoundException;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements DefaultService<User>, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BasketRepository basketRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("User with id: " + id + " was not found"));
    }

    @Override
    @Transactional(rollbackFor = ConstraintViolationException.class)
    public User save(User user) {
        checkUsernameAndEmail(user.getUsername(), user.getEmail());
        Basket basket = new Basket();
        basketRepository.save(basket);
        user.setRoles(Set.of(roleRepository.findByName(EnumRole.ROLE_USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBasket(basket);

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existUser = findById(user.getId());
        user.setPassword(existUser.getPassword());

        if (!user.getUsername().equals(existUser.getUsername()) ||
                !user.getEmail().equals(existUser.getEmail())) {
            checkUsernameAndEmail(user.getUsername(), user.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public User deleteById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }

    /**
     * Method that checks login and email of user.
     *
     * @param username user's username
     * @param email    user's email
     */
    @Override
    public void checkUsernameAndEmail(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UserExistException("Login = " + username + " are existed already");
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserExistException("Email = " + email + " are existed already");
        }
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.warn("User not found with username: " + username);
            throw new UserNotFoundException("User not found with username = " + username);
        }
        return user;
    }

}
