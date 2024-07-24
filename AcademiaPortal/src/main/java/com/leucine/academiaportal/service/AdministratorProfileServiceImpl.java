package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.AdministratorProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.AdministratorProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link AdministratorProfileService} interface.
 * Provides methods for saving and retrieving {@link AdministratorProfile} entities.
 */
@Service
@Slf4j
public class AdministratorProfileServiceImpl implements AdministratorProfileService {

    @Autowired
    public AdministratorProfileRepository administratorProfileRepository;

    /**
     * Saves a new or existing {@link AdministratorProfile} entity.
     * This method is responsible for persisting the administrator profile data.
     *
     * @param administratorProfile the administrator profile to be saved
     * @return the saved {@link AdministratorProfile} entity
     * @throws AcademiaPortalException if the provided profile is null
     */
    @Override
    public AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) throws AcademiaPortalException {
        // Check if the profile is null
        Optional.ofNullable(administratorProfile)
                .orElseThrow(() -> {
                    log.error("Cannot save null AdministratorProfile");
                    return new AcademiaPortalException("AdministratorProfile cannot be null");
                });

        // Log and save the profile
        log.info("Saving AdministratorProfile with ID: {}", administratorProfile.getUserId());
        AdministratorProfile savedProfile = administratorProfileRepository.save(administratorProfile);
        log.info("Successfully saved AdministratorProfile with ID: {}", savedProfile.getUserId());
        return savedProfile;
    }

    /**
     * Retrieves an {@link AdministratorProfile} entity by its ID.
     * This method fetches the administrator profile data based on the provided ID.
     *
     * @param id the ID of the administrator profile to retrieve
     * @return the {@link AdministratorProfile} entity associated with the given ID
     * @throws AcademiaPortalException if the profile with the given ID is not found
     */
    @Override
    public AdministratorProfile getAdministratorProfile(Long id) throws AcademiaPortalException {
        log.info("Retrieving AdministratorProfile with ID: {}", id);

        return administratorProfileRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No AdministratorProfile found with ID: {}", id);
                    return new AcademiaPortalException("No AdministratorProfile found with ID: " + id);
                });
    }
}
