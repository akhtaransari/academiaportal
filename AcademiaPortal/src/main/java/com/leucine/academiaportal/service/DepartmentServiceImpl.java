package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.Department;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link Department} entities.
 * This class provides methods to save, retrieve, and list departments with proper logging and exception handling.
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    public DepartmentRepository departmentRepository;

    /**
     * Saves a {@link Department} entity.
     * Validates the department object before saving it to the repository.
     * Logs the saving operation and throws an {@link AcademiaPortalException} if the department is null.
     *
     * @param department the department entity to be saved
     * @return the saved department entity
     * @throws AcademiaPortalException if the department cannot be saved
     */
    @Override
    public Department saveDepartment(Department department) throws AcademiaPortalException {
        log.info("Saving department with name: {}", department.getName());

        // Validate the department object is not null
        return Optional.ofNullable(department)
                .map(dept -> departmentRepository.save(dept))
                .orElseThrow(() -> {
                    String errorMessage = "Failed to save department with name: " + department.getName();
                    log.error(errorMessage);
                    return new AcademiaPortalException(errorMessage);
                });
    }

    /**
     * Retrieves a {@link Department} entity by its ID.
     * Fetches the department from the repository and throws an {@link AcademiaPortalException} if the department is not found.
     * Logs the retrieval operation and the result.
     *
     * @param id the ID of the department to retrieve
     * @return the department entity associated with the given ID
     * @throws AcademiaPortalException if the department with the given ID is not found
     */
    @Override
    public Department getDepartment(Long id) throws AcademiaPortalException {
        log.info("Fetching department with ID: {}", id);

        // Retrieve the department from the repository and handle the case when it's not found
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Department not found with ID: " + id;
                    log.warn(errorMessage);
                    return new AcademiaPortalException(errorMessage);
                });
    }

    /**
     * Retrieves all {@link Department} entities.
     * Fetches all departments from the repository and throws an {@link AcademiaPortalException} if no departments are found.
     * Logs the number of departments retrieved and any issues encountered.
     *
     * @return a list of all departments
     * @throws AcademiaPortalException if no departments are found in the repository
     */
    @Override
    public List<Department> getAllDepartments() throws AcademiaPortalException {
        log.info("Fetching all departments");

        // Retrieve all departments from the repository
        List<Department> departments = departmentRepository.findAll();

        // Handle the case when no departments are found
        return Optional.of(departments)
                .filter(list -> !list.isEmpty()) // Check if the list is not empty
                .map(list -> {
                    log.info("Number of departments found: {}", list.size());
                    return list;
                })
                .orElseThrow(() -> {
                    String errorMessage = "No departments found";
                    log.warn(errorMessage);
                    return new AcademiaPortalException(errorMessage);
                });
    }
}
