package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.Department;
import com.leucine.academiaportal.exception.AcademiaPortalException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing {@link Department} entities.
 */
public interface DepartmentService {

    /**
     * Saves a {@link Department} entity.
     *
     * @param department the department entity to be saved
     * @return the saved department entity
     * @throws AcademiaPortalException if the department could not be saved
     */
    Department saveDepartment(Department department) throws AcademiaPortalException;

    /**
     * Retrieves a {@link Department} entity by its ID.
     *
     * @param id the ID of the department to retrieve
     * @return the department if found
     * @throws AcademiaPortalException if the department with the given ID is not found
     */
    Department getDepartment(Long id) throws AcademiaPortalException;

    /**
     * Retrieves all {@link Department} entities.
     *
     * @return a list of all departments
     * @throws AcademiaPortalException if no departments are found
     */
    List<Department> getAllDepartments() throws AcademiaPortalException;
}
