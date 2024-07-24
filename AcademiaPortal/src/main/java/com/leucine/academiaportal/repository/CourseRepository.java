package com.leucine.academiaportal.repository;

import com.leucine.academiaportal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Course} entities.
 * This interface extends JpaRepository to provide basic CRUD operations and additional query capabilities.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    // JpaRepository provides basic CRUD operations (save, findById, findAll, deleteById) out of the box.
    // You can add custom query methods if needed here.
}
