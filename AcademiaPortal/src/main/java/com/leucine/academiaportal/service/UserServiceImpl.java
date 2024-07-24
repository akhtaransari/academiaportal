package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.User;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing {@link User} entities.
 * This service provides methods to find and save users, including logging and exception handling.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds a user by username or email.
     * Searches for a user with the given username or email in the repository.
     *
     * @param usernameOrEmail the username or email to search for
     * @return an {@link Optional} containing the found user, or an empty {@link Optional} if no user is found
     * @throws AcademiaPortalException if no user is found with the provided username or email
     */
    @Override
    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        log.info("Searching for user with username or email: {}", usernameOrEmail);

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(usernameOrEmail);
        return userOptional
                .map(user -> {
                    log.info("User found with username or email: {}", usernameOrEmail);
                    return Optional.of(user);
                })
                .orElseGet(() -> {
                    String errorMessage = "No user found with username or email: " + usernameOrEmail;
                    log.warn(errorMessage);
                    throw new AcademiaPortalException(errorMessage);
                });
    }

    /**
     * Saves a new user.
     * Checks if a user with the given email already exists before saving the new user.
     *
     * @param user the {@link User} entity to save
     * @return the saved {@link User} entity
     * @throws AcademiaPortalException if a user with the provided email already exists
     */
    @Override
    public User saveUser(User user) {
        log.info("Attempting to save user with email: {}", user.getEmail());

        boolean emailExists = userRepository.findByUsernameOrEmail(user.getEmail()).isPresent();
        if (emailExists) {
            String errorMessage = "Email is already registered: " + user.getEmail();
            log.warn(errorMessage);
            throw new AcademiaPortalException(errorMessage);
        }

        log.info("Saving user with username: {}", user.getUsername());
        return userRepository.save(user);
    }
}
