package com.leucine.academiaportal;

import com.leucine.academiaportal.entity.Department;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.DepartmentRepository;
import com.leucine.academiaportal.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link DepartmentServiceImpl}.
 * This class contains test cases for methods in the DepartmentServiceImpl class.
 */
public class DepartmentServiceImplTest {

    // Mocked repository used for testing
    private DepartmentRepository departmentRepository;

    // Service instance to be tested
    private DepartmentServiceImpl departmentService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mocked repository and the service instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mock repository
        departmentRepository = mock(DepartmentRepository.class);

        // Create an instance of the service and inject the mock repository
        departmentService = new DepartmentServiceImpl();
        departmentService.departmentRepository = departmentRepository;
    }

    /**
     * Tests the saving of a {@link Department}.
     * Verifies that the department is saved correctly and the repository's save method is called once.
     */
    @Test
    void testSaveDepartment() {
        // Create a sample department with a name
        Department department = new Department();
        department.setName("Computer Science");

        // Define the behavior of the mock repository for the save operation
        when(departmentRepository.save(department)).thenReturn(department);

        // Call the save method of the service
        Department savedDepartment = departmentService.saveDepartment(department);

        // Verify that the saved department is not null and the name is correct
        assertNotNull(savedDepartment);
        assertEquals("Computer Science", savedDepartment.getName());

        // Verify that the repository's save method was called once
        verify(departmentRepository, times(1)).save(department);
    }

    /**
     * Tests the retrieval of a {@link Department} by ID when the department exists.
     * Verifies that the department is found and the repository's findById method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetDepartment() throws AcademiaPortalException {
        // Create a sample department with an ID and name
        Department department = new Department();
        department.setId(1L);
        department.setName("Computer Science");

        // Define the behavior of the mock repository for the findById operation
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // Call the get method of the service
        Department foundDepartment = departmentService.getDepartment(1L);

        // Verify that the found department is not null and the name is correct
        assertNotNull(foundDepartment);
        assertEquals("Computer Science", foundDepartment.getName());

        // Verify that the repository's findById method was called once
        verify(departmentRepository, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of a {@link Department} by ID when the department does not exist.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetDepartmentNotFound() {
        // Define the behavior of the mock repository for the findById operation when the department is not found
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the get method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            departmentService.getDepartment(1L);
        });

        // Verify that the exception message is as expected
        assertEquals("Department not found with ID: 1", thrown.getMessage());
    }

    /**
     * Tests the retrieval of all {@link Department} entities.
     * Verifies that the list of departments is returned correctly and the repository's findAll method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetAllDepartments() throws AcademiaPortalException {
        // Create a sample department and a list containing that department
        Department department = new Department();
        department.setName("Computer Science");
        List<Department> departmentList = Collections.singletonList(department);

        // Define the behavior of the mock repository for the findAll operation
        when(departmentRepository.findAll()).thenReturn(departmentList);

        // Call the getAllDepartments method of the service
        List<Department> departments = departmentService.getAllDepartments();

        // Verify that the list of departments is not null and contains the expected department
        assertNotNull(departments);
        assertFalse(departments.isEmpty());
        assertEquals(1, departments.size());
        assertEquals("Computer Science", departments.get(0).getName());

        // Verify that the repository's findAll method was called once
        verify(departmentRepository, times(1)).findAll();
    }

    /**
     * Tests the retrieval of all {@link Department} entities when no departments are available.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetAllDepartmentsNotFound() {
        // Define the behavior of the mock repository for the findAll operation when no departments are found
        when(departmentRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the getAllDepartments method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            departmentService.getAllDepartments();
        });

        // Verify that the exception message is as expected
        assertEquals("No departments found", thrown.getMessage());
    }
}
