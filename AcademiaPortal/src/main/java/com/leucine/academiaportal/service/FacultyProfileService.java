package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.FacultyProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;

import java.util.Optional;

/**
 * Service interface for managing {@link FacultyProfile} entities.
 */
public interface FacultyProfileService {

    /**
     * Saves a {@link FacultyProfile} entity.
     *
     * @param facultyProfile the faculty profile entity to be saved
     * @return the saved faculty profile entity
     */
    FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile);

    /**
     * Retrieves a {@link FacultyProfile} entity by its ID.
     *
     * @param id the ID of the faculty profile to retrieve
     * @return an {@link Optional} containing the faculty profile if found
     * @throws AcademiaPortalException if the faculty profile with the given ID is not found
     */
    FacultyProfile getFacultyProfile(Long id) throws AcademiaPortalException;
}
