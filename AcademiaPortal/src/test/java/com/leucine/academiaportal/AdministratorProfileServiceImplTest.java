package com.leucine.academiaportal;

import com.leucine.academiaportal.entity.AdministratorProfile;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.AdministratorProfileRepository;
import com.leucine.academiaportal.service.AdministratorProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link AdministratorProfileServiceImpl}.
 * This class contains test cases for the methods defined in the service class.
 */
public class AdministratorProfileServiceImplTest {

    // Mocked repository used for testing
    private AdministratorProfileRepository administratorProfileRepository;

    // Service instance to be tested
    private AdministratorProfileServiceImpl administratorProfileService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mocked repository and the service instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mock repository
        administratorProfileRepository = mock(AdministratorProfileRepository.class);

        // Create an instance of the service and inject the mock repository
        administratorProfileService = new AdministratorProfileServiceImpl();
        administratorProfileService.administratorProfileRepository = administratorProfileRepository;
    }

    /**
     * Tests the saving of an {@link AdministratorProfile}.
     * Verifies that the profile is saved correctly and the repository's save method is called once.
     */
    @Test
    void testSaveAdministratorProfile() {
        // Create a sample profile with a user ID
        AdministratorProfile profile = new AdministratorProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the save operation
        when(administratorProfileRepository.save(profile)).thenReturn(profile);

        // Call the save method of the service
        AdministratorProfile savedProfile = administratorProfileService.saveAdministratorProfile(profile);

        // Verify that the saved profile is not null and the user ID is correct
        assertNotNull(savedProfile);
        assertEquals(1L, savedProfile.getUserId());

        // Verify that the repository's save method was called once
        verify(administratorProfileRepository, times(1)).save(profile);
    }

    /**
     * Tests the retrieval of an {@link AdministratorProfile} by ID when the profile exists.
     * Verifies that the profile is found and the repository's findById method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetAdministratorProfile() throws AcademiaPortalException {
        // Create a sample profile with a user ID
        AdministratorProfile profile = new AdministratorProfile();
        profile.setUserId(1L);

        // Define the behavior of the mock repository for the findById operation
        when(administratorProfileRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Call the get method of the service
        AdministratorProfile foundProfile = administratorProfileService.getAdministratorProfile(1L);

        // Verify that the found profile is not null and the user ID is correct
        assertNotNull(foundProfile);
        assertEquals(1L, foundProfile.getUserId());

        // Verify that the repository's findById method was called once
        verify(administratorProfileRepository, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of an {@link AdministratorProfile} by ID when the profile does not exist.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetAdministratorProfileNotFound() {
        // Define the behavior of the mock repository for the findById operation when the profile is not found
        when(administratorProfileRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the get method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            administratorProfileService.getAdministratorProfile(1L);
        });

        // Verify that the exception message is as expected
        assertEquals("No AdministratorProfile found with ID: 1", thrown.getMessage());
    }
}
