package com.leucine.academiaportal.repository;

import com.leucine.academiaportal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link StudentProfile} entities.
 * This interface extends JpaRepository to provide basic CRUD operations and additional query capabilities.
 */
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    // JpaRepository provides basic CRUD operations (save, findById, findAll, deleteById) out of the box.
    // Custom query methods can be defined here if needed.
}
