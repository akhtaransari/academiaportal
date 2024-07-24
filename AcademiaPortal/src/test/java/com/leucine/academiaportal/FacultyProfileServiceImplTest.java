package com.leucine.academiaportal;

import com.leucine.academiaportal.entity.FacultyProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.FacultyProfileRepository;
import com.leucine.academiaportal.service.FacultyProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link FacultyProfileServiceImpl}.
 * This class contains test cases for methods in the FacultyProfileServiceImpl class.
 */
public class FacultyProfileServiceImplTest {

    // Mocked repository used for testing
    private FacultyProfileRepository facultyProfileRepository;

    // Service instance to be tested
    private FacultyProfileServiceImpl facultyProfileService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mocked repository and the service instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mock repository
        facultyProfileRepository = mock(FacultyProfileRepository.class);

        // Create an instance of the service and inject the mock repository
        facultyProfileService = new FacultyProfileServiceImpl();
        facultyProfileService.facultyProfileRepository = facultyProfileRepository;
    }

    /**
     * Tests the saving of a {@link FacultyProfile}.
     * Verifies that the faculty profile is saved correctly and the repository's save method is called once.
     */
    @Test
    void testSaveFacultyProfile() {
        // Create a sample faculty profile with a user ID
        FacultyProfile profile = new FacultyProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the save operation
        when(facultyProfileRepository.save(profile)).thenReturn(profile);

        // Call the save method of the service
        FacultyProfile savedProfile = facultyProfileService.saveFacultyProfile(profile);

        // Verify that the saved profile is not null and the user ID is correct
        assertNotNull(savedProfile);
        assertEquals(1L, savedProfile.getUserId());

        // Verify that the repository's save method was called once
        verify(facultyProfileRepository, times(1)).save(profile);
    }

    /**
     * Tests the retrieval of a {@link FacultyProfile} by ID when the profile exists.
     * Verifies that the profile is found and the repository's findById method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetFacultyProfile() throws AcademiaPortalException {
        // Create a sample faculty profile with a user ID
        FacultyProfile profile = new FacultyProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the findById operation
        when(facultyProfileRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Call the get method of the service
        FacultyProfile foundProfile = facultyProfileService.getFacultyProfile(1L);

        // Verify that the found profile is not null and the user ID is correct
        assertNotNull(foundProfile);
        assertEquals(1L, foundProfile.getUserId());

        // Verify that the repository's findById method was called once
        verify(facultyProfileRepository, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of a {@link FacultyProfile} by ID when the profile does not exist.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetFacultyProfileNotFound() {
        // Define the behavior of the mock repository for the findById operation when the profile is not found
        when(facultyProfileRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the get method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            facultyProfileService.getFacultyProfile(1L);
        });

        // Verify that the exception message is as expected
        assertEquals("Faculty profile not found with ID: 1", thrown.getMessage());
    }
}
