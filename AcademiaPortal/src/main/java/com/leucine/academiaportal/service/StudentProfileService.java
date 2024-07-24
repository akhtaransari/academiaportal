package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.StudentProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;

import java.util.Optional;

/**
 * Service interface for managing {@link StudentProfile} entities.
 * Provides methods to save and retrieve student profiles.
 */
public interface StudentProfileService {

    /**
     * Saves a {@link StudentProfile} entity.
     *
     * @param studentProfile the student profile entity to be saved
     * @return the saved student profile entity
     */
    StudentProfile saveStudentProfile(StudentProfile studentProfile);

    /**
     * Retrieves a {@link StudentProfile} entity by its ID.
     * The method returns an {@link Optional} that will contain the student profile if found.
     * If not found, the implementation should throw an {@link AcademiaPortalException}.
     *
     * @param id the ID of the student profile to retrieve
     * @return an {@link Optional} containing the student profile if found, or empty if not
     * @throws AcademiaPortalException if the student profile with the given ID is not found
     */
    Optional<StudentProfile> getStudentProfile(Long id) throws AcademiaPortalException;
}
