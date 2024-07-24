package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.Course;
import com.leucine.academiaportal.exception.AcademiaPortalException;

import java.util.List;

/**
 * Service interface for managing {@link Course} entities.
 */
public interface CourseService {

    /**
     * Saves a {@link Course} entity.
     *
     * @param course the course entity to be saved
     * @return the saved course entity
     * @throws AcademiaPortalException if the course entity is null
     */
    Course saveCourse(Course course) throws AcademiaPortalException;

    /**
     * Retrieves a {@link Course} entity by its ID.
     *
     * @param id the ID of the course to retrieve
     * @return the course entity if found
     * @throws AcademiaPortalException if the course with the given ID is not found
     */
    Course getCourse(Long id) throws AcademiaPortalException;

    /**
     * Retrieves all {@link Course} entities.
     *
     * @return a list of all courses
     * @throws AcademiaPortalException if no courses are found
     */
    List<Course> getAllCourses() throws AcademiaPortalException;
}
