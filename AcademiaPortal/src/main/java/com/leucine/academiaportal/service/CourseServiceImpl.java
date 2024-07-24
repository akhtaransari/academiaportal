package com.leucine.academiaportal.service;

import com.leucine.academiaportal.entity.Course;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link Course} entities.
 * This class provides methods to save, retrieve, and list courses with proper logging and exception handling.
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    public CourseRepository courseRepository;

    /**
     * Saves a {@link Course} entity.
     * Validates that the course object is not null before saving it to the repository.
     * Logs the saving operation and throws an {@link AcademiaPortalException} if the course is null.
     *
     * @param course the course entity to be saved
     * @return the saved course entity
     * @throws AcademiaPortalException if the provided course is null
     */
    @Override
    public Course saveCourse(Course course) throws AcademiaPortalException {
        log.info("Saving course with title: {}", course.getTitle());

        // Validate that the course object is not null
        Optional.ofNullable(course)
                .orElseThrow(() -> {
                    log.warn("Course is null");
                    return new AcademiaPortalException("Course cannot be null");
                });

        // Save the course to the repository and return the saved entity
        return courseRepository.save(course);
    }

    /**
     * Retrieves a {@link Course} entity by its ID.
     * Fetches the course from the repository and throws an {@link AcademiaPortalException} if the course is not found.
     * Logs the retrieval operation and the result.
     *
     * @param id the ID of the course to retrieve
     * @return the course entity associated with the given ID
     * @throws AcademiaPortalException if the course with the given ID is not found
     */
    @Override
    public Course getCourse(Long id) throws AcademiaPortalException {
        log.info("Fetching course with ID: {}", id);

        // Retrieve the course from the repository and handle the case when it's not found
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Course with ID {} not found", id);
                    return new AcademiaPortalException("Course not found with ID: " + id);
                });
    }

    /**
     * Retrieves all {@link Course} entities.
     * Fetches all courses from the repository and throws an {@link AcademiaPortalException} if no courses are found.
     * Logs the number of courses retrieved and any issues encountered.
     *
     * @return a list of all courses
     * @throws AcademiaPortalException if no courses are found in the repository
     */
    @Override
    public List<Course> getAllCourses() throws AcademiaPortalException {
        log.info("Fetching all courses");

        // Retrieve all courses from the repository
        List<Course> courses = courseRepository.findAll();

        // Handle the case when no courses are found
        return Optional.ofNullable(courses)
                .filter(list -> !list.isEmpty()) // Check if the list is not empty
                .map(list -> {
                    log.info("Number of courses found: {}", list.size());
                    return list;
                })
                .orElseThrow(() -> {
                    log.warn("No courses found");
                    return new AcademiaPortalException("No courses found");
                });
    }
}
