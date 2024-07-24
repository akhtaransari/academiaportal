package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.FacultyProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.FacultyProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing {@link FacultyProfile} entities.
 * This class provides methods to save and retrieve faculty profiles with appropriate logging and exception handling.
 */
@Service
@Slf4j
public class FacultyProfileServiceImpl implements FacultyProfileService {

    @Autowired
    public FacultyProfileRepository facultyProfileRepository;

    /**
     * Saves a {@link FacultyProfile} entity.
     * This method is responsible for persisting the faculty profile data to the repository.
     * Logs the saving operation and returns the saved profile.
     *
     * @param facultyProfile the faculty profile entity to be saved
     * @return the saved {@link FacultyProfile} entity
     */
    @Override
    public FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile) {
        log.info("Saving faculty profile for user ID: {}", facultyProfile.getUserId());
        // Save the faculty profile to the repository
        return facultyProfileRepository.save(facultyProfile);
    }

    /**
     * Retrieves a {@link FacultyProfile} entity by its ID.
     * This method fetches the faculty profile data based on the provided ID.
     * Uses {@link Optional} to handle cases where the profile might not be found.
     * Logs the retrieval operation and throws an {@link AcademiaPortalException} if the profile is not found.
     *
     * @param id the ID of the faculty profile to retrieve
     * @return the {@link FacultyProfile} entity associated with the given ID
     * @throws AcademiaPortalException if the faculty profile with the given ID is not found
     */
    @Override
    public FacultyProfile getFacultyProfile(Long id) throws AcademiaPortalException {
        log.info("Fetching faculty profile with ID: {}", id);

        // Retrieve the faculty profile from the repository and handle the case when it's not found
        return facultyProfileRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Faculty profile not found with ID: " + id;
                    log.warn(errorMessage);
                    return new AcademiaPortalException(errorMessage);
                });
    }
}
