package com.leucine.academiaportal.controller;

import com.leucine.academiaportal.entity.StudentProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.service.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for managing student profiles.
 * This controller provides endpoints for creating and retrieving student profiles.
 */
@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentProfileService studentProfileService;

    /**
     * Creates a new student profile.
     * This endpoint saves the provided student profile to the database.
     *
     * @param studentProfile the StudentProfile object containing profile details
     * @return a ResponseEntity containing the saved student profile and HTTP status CREATED
     */
    @PostMapping("/profile")
    public ResponseEntity<StudentProfile> createProfile(@RequestBody StudentProfile studentProfile) {
        log.info("Creating student profile for: {}", studentProfile.getUser().getName());
        StudentProfile savedProfile = studentProfileService.saveStudentProfile(studentProfile);
        log.info("Student profile created with ID: {}", savedProfile.getUserId());
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    /**
     * Retrieves a student profile by its ID.
     * This endpoint fetches the profile details of the specified student.
     *
     * @param id the ID of the student profile to retrieve
     * @return a ResponseEntity containing the retrieved student profile and HTTP status OK
     * @throws AcademiaPortalException if the profile with the specified ID is not found
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<StudentProfile> getProfile(@PathVariable Long id) {
        log.info("Fetching student profile with ID: {}", id);
        Optional<StudentProfile> profile = studentProfileService.getStudentProfile(id);
        log.info("Student profile retrieved with ID: {}", id);
        return new ResponseEntity<>(profile.get(), HttpStatus.OK);
    }
}
