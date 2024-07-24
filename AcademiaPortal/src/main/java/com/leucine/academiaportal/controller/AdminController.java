package com.leucine.academiaportal.controller;

import com.leucine.academiaportal.entity.AdministratorProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.service.AdministratorProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing administrator profiles.
 * This controller provides endpoints for creating and retrieving administrator profiles.
 */
@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    /**
     * Creates a new administrator profile.
     * This endpoint saves the provided administrator profile to the database.
     *
     * @param administratorProfile the AdministratorProfile object containing profile details
     * @return a ResponseEntity containing the saved administrator profile and HTTP status CREATED
     */
    @PostMapping("/profile")
    public ResponseEntity<AdministratorProfile> createProfile(@RequestBody AdministratorProfile administratorProfile) {
        log.info("Creating profile for administrator: {}", administratorProfile.getUser().getUsername());
        AdministratorProfile savedProfile = administratorProfileService.saveAdministratorProfile(administratorProfile);
        log.info("Administrator profile created with ID: {}", savedProfile.getUserId());
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    /**
     * Retrieves an administrator profile by its ID.
     * This endpoint fetches the profile details of the specified administrator.
     *
     * @param id the ID of the administrator profile to retrieve
     * @return a ResponseEntity containing the retrieved administrator profile and HTTP status OK
     * @throws AcademiaPortalException if the profile with the specified ID is not found
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<AdministratorProfile> getProfile(@PathVariable Long id) {
        log.info("Fetching administrator profile with ID: {}", id);
        AdministratorProfile profile = administratorProfileService.getAdministratorProfile(id);
        log.info("Administrator profile retrieved with ID: {}", id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
