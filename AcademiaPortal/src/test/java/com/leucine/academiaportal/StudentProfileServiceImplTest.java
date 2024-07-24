package com.leucine.academiaportal;

import com.leucine.academiaportal.entity.StudentProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.StudentProfileRepository;
import com.leucine.academiaportal.service.StudentProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link StudentProfileServiceImpl}.
 * This class contains test cases for methods in the StudentProfileServiceImpl class.
 */
public class StudentProfileServiceImplTest {

    // Mocked repository used for testing
    private StudentProfileRepository studentProfileRepository;

    // Service instance to be tested
    private StudentProfileServiceImpl studentProfileService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mocked repository and the service instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mock repository
        studentProfileRepository = mock(StudentProfileRepository.class);

        // Create an instance of the service and inject the mock repository
        studentProfileService = new StudentProfileServiceImpl();
        studentProfileService.studentProfileRepository = studentProfileRepository;
    }

    /**
     * Tests the saving of a {@link StudentProfile}.
     * Verifies that the student profile is saved correctly and the repository's save method is called once.
     */
    @Test
    void testSaveStudentProfile() {
        // Create a sample student profile with a user ID
        StudentProfile profile = new StudentProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the save operation
        when(studentProfileRepository.save(profile)).thenReturn(profile);

        // Call the save method of the service
        StudentProfile savedProfile = studentProfileService.saveStudentProfile(profile);

        // Verify that the saved profile is not null and the user ID is correct
        assertNotNull(savedProfile);
        assertEquals(1L, savedProfile.getUserId());

        // Verify that the repository's save method was called once
        verify(studentProfileRepository, times(1)).save(profile);
    }

    /**
     * Tests the retrieval of a {@link StudentProfile} by ID when the profile exists.
     * Verifies that the profile is found and the repository's findById method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetStudentProfile() throws AcademiaPortalException {
        // Create a sample student profile with a user ID
        StudentProfile profile = new StudentProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the findById operation
        when(studentProfileRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Call the get method of the service
        Optional<StudentProfile> foundProfile = studentProfileService.getStudentProfile(1L);

        // Verify that the found profile is not null and the user ID is correct
        assertTrue(foundProfile.isPresent());
        assertEquals(1L, foundProfile.get().getUserId());

        // Verify that the repository's findById method was called once
        verify(studentProfileRepository, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of a {@link StudentProfile} by ID when the profile does not exist.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetStudentProfileNotFound() {
        // Define the behavior of the mock repository for the findById operation when the profile is not found
        when(studentProfileRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the get method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            studentProfileService.getStudentProfile(1L);
        });

        // Verify that the exception message is as expected
        assertEquals("Student profile not found with ID: 1", thrown.getMessage());
    }
}
