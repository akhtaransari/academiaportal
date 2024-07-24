package com.leucine.academiaportal.controller;

import com.leucine.academiaportal.entity.FacultyProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.service.FacultyProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing faculty profiles.
 * This controller provides endpoints for creating and retrieving faculty profiles.
 */
@RestController
@RequestMapping("/api/faculty")
@Slf4j
public class FacultyController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    /**
     * Creates a new faculty profile.
     * This endpoint saves the provided faculty profile to the database.
     *
     * @param facultyProfile the FacultyProfile object containing profile details
     * @return a ResponseEntity containing the saved faculty profile and HTTP status CREATED
     */
    @PostMapping("/profile")
    public ResponseEntity<FacultyProfile> createProfile(@RequestBody FacultyProfile facultyProfile) {
        log.info("Creating faculty profile for: {}", facultyProfile.getUser().getName());
        FacultyProfile savedProfile = facultyProfileService.saveFacultyProfile(facultyProfile);
        log.info("Faculty profile created with ID: {}", savedProfile.getUserId());
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    /**
     * Retrieves a faculty profile by its ID.
     * This endpoint fetches the profile details of the specified faculty.
     *
     * @param id the ID of the faculty profile to retrieve
     * @return a ResponseEntity containing the retrieved faculty profile and HTTP status OK
     * @throws AcademiaPortalException if the profile with the specified ID is not found
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<FacultyProfile> getProfile(@PathVariable Long id) {
        log.info("Fetching faculty profile with ID: {}", id);
        FacultyProfile profile = facultyProfileService.getFacultyProfile(id);
        log.info("Faculty profile retrieved with ID: {}", id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
