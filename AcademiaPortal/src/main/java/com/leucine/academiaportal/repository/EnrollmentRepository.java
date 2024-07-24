package com.leucine.academiaportal.repository;

import com.leucine.academiaportal.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Enrollment} entities.
 * This interface extends JpaRepository to provide basic CRUD operations and additional query capabilities.
 */
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // JpaRepository provides basic CRUD operations (save, findById, findAll, deleteById) out of the box.
    // Custom query methods can be defined here if needed.
}
