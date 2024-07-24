package com.leucine.academiaportal;

import com.leucine.academiaportal.entity.Course;
import com.leucine.academiaportal.exception.AcademiaPortalException;
import com.leucine.academiaportal.repository.CourseRepository;
import com.leucine.academiaportal.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link CourseServiceImpl}.
 * This class contains test cases for methods in the CourseServiceImpl class.
 */
public class CourseServiceImplTest {

    // Mocked repository used for testing
    private CourseRepository courseRepository;

    // Service instance to be tested
    private CourseServiceImpl courseService;

    /**
     * Sets up the test environment before each test case.
     * Initializes the mocked repository and the service instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mock repository
        courseRepository = mock(CourseRepository.class);

        // Create an instance of the service and inject the mock repository
        courseService = new CourseServiceImpl();
        courseService.courseRepository = courseRepository;
    }

    /**
     * Tests the saving of a {@link Course}.
     * Verifies that the course is saved correctly and the repository's save method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during saving
     */
    @Test
    void testSaveCourse() throws AcademiaPortalException {
        // Create a sample course with a title
        Course course = new Course();
        course.setTitle("Java Programming");

        // Define the behavior of the mock repository for the save operation
        when(courseRepository.save(course)).thenReturn(course);

        // Call the save method of the service
        Course savedCourse = courseService.saveCourse(course);

        // Verify that the saved course is not null and the title is correct
        assertNotNull(savedCourse);
        assertEquals("Java Programming", savedCourse.getTitle());

        // Verify that the repository's save method was called once
        verify(courseRepository, times(1)).save(course);
    }

    /**
     * Tests the retrieval of a {@link Course} by ID when the course exists.
     * Verifies that the course is found and the repository's findById method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetCourse() throws AcademiaPortalException {
        // Create a sample course with an ID and title
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");

        // Define the behavior of the mock repository for the findById operation
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Call the get method of the service
        Course foundCourse = courseService.getCourse(1L);

        // Verify that the found course is not null and the title is correct
        assertNotNull(foundCourse);
        assertEquals("Java Programming", foundCourse.getTitle());

        // Verify that the repository's findById method was called once
        verify(courseRepository, times(1)).findById(1L);
    }

    /**
     * Tests the retrieval of a {@link Course} by ID when the course does not exist.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetCourseNotFound() {
        // Define the behavior of the mock repository for the findById operation when the course is not found
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the get method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            courseService.getCourse(1L);
        });

        // Verify that the exception message is as expected
        assertEquals("Course not found with ID: 1", thrown.getMessage());
    }

    /**
     * Tests the retrieval of all {@link Course} entities.
     * Verifies that the list of courses is returned correctly and the repository's findAll method is called once.
     *
     * @throws AcademiaPortalException if an error occurs during retrieval
     */
    @Test
    void testGetAllCourses() throws AcademiaPortalException {
        // Create a sample course and a list containing that course
        Course course = new Course();
        course.setTitle("Java Programming");
        List<Course> courseList = Collections.singletonList(course);

        // Define the behavior of the mock repository for the findAll operation
        when(courseRepository.findAll()).thenReturn(courseList);

        // Call the getAllCourses method of the service
        List<Course> courses = courseService.getAllCourses();

        // Verify that the list of courses is not null and contains the expected course
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        assertEquals("Java Programming", courses.get(0).getTitle());

        // Verify that the repository's findAll method was called once
        verify(courseRepository, times(1)).findAll();
    }

    /**
     * Tests the retrieval of all {@link Course} entities when no courses are available.
     * Verifies that an {@link AcademiaPortalException} is thrown with the correct message.
     */
    @Test
    void testGetAllCoursesNotFound() {
        // Define the behavior of the mock repository for the findAll operation when no courses are found
        when(courseRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the getAllCourses method of the service and verify that an exception is thrown
        AcademiaPortalException thrown = assertThrows(AcademiaPortalException.class, () -> {
            courseService.getAllCourses();
        });

        // Verify that the exception message is as expected
        assertEquals("No courses found", thrown.getMessage());
    }
}
