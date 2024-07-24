package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.User;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * Service interface for managing {@link User} entities.
 * Provides methods for finding users by username or email and saving user entities.
 */
public interface UserService {

    /**
     * Finds a {@link User} entity by either username or email.
     * This method returns an {@link Optional} containing the user if found, or an empty {@link Optional} if not found.
     *
     * @param usernameOrEmail the username or email of the user to find
     * @return an {@link Optional} containing the user if found, or an empty {@link Optional} if not found
     * @throws AcademiaPortalException if the provided username or email is invalid
     */
    Optional<User> findByUsernameOrEmail(@Nullable String usernameOrEmail) throws AcademiaPortalException;

    /**
     * Saves a {@link User} entity.
     * This method will save the user entity to the repository. If the user with the given email already exists,
     * an {@link AcademiaPortalException} will be thrown.
     *
     * @param user the user entity to be saved
     * @return the saved user entity
     * @throws AcademiaPortalException if the user with the given email already exists
     */
    User saveUser(User user) throws AcademiaPortalException;
}
