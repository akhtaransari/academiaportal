package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.StudentProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing {@link StudentProfile} entities.
 * Provides methods to save and retrieve student profiles with logging and exception handling.
 */
@Service
@Slf4j
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    public StudentProfileRepository studentProfileRepository;

    /**
     * Saves a {@link StudentProfile} entity.
     *
     * @param studentProfile the student profile to be saved
     * @return the saved student profile entity
     */
    @Override
    public StudentProfile saveStudentProfile(StudentProfile studentProfile) {
        log.info("Saving student profile for user ID: {}", studentProfile.getUserId());
        return studentProfileRepository.save(studentProfile);
    }

    /**
     * Retrieves a {@link StudentProfile} entity by its ID.
     * Throws an {@link AcademiaPortalException} if the profile is not found.
     *
     * @param id the ID of the student profile to be retrieved
     * @return an {@link Optional} containing the student profile if found, or empty if not
     * @throws AcademiaPortalException if the student profile with the given ID is not found
     */
    @Override
    public Optional<StudentProfile> getStudentProfile(Long id) throws AcademiaPortalException {
        log.info("Fetching student profile with ID: {}", id);

        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(id);

        return studentProfile
                .map(profile -> {
                    log.info("Successfully retrieved student profile with ID: {}", id);
                    return Optional.of(profile);
                })
                .orElseThrow(() -> {
                    String errorMessage = "Student profile not found with ID: " + id;
                    log.warn(errorMessage);
                    return new AcademiaPortalException(errorMessage);
                });
    }
}
