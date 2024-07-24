package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.AdministratorProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;

/**
 * Service interface for managing {@link AdministratorProfile} entities.
 * Provides methods for saving and retrieving administrator profiles.
 */
public interface AdministratorProfileService {

    /**
     * Saves a new or existing {@link AdministratorProfile} entity.
     * This method is responsible for persisting the administrator profile data.
     *
     * @param administratorProfile the administrator profile to be saved
     * @return the saved {@link AdministratorProfile} entity
     * @throws AcademiaPortalException if the profile cannot be saved due to invalid data
     */
    AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) throws AcademiaPortalException;

    /**
     * Retrieves an {@link AdministratorProfile} entity by its ID.
     * This method fetches the administrator profile data based on the provided ID.
     *
     * @param id the ID of the administrator profile to retrieve
     * @return the {@link AdministratorProfile} entity associated with the given ID
     * @throws AcademiaPortalException if the profile with the given ID is not found
     */
    AdministratorProfile getAdministratorProfile(Long id) throws AcademiaPortalException;
}
