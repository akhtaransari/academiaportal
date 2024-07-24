package com.leucine.academiaportal.repository;

import com.leucine.academiaportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Department} entities.
 * This interface extends JpaRepository to provide basic CRUD operations and additional query capabilities.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // JpaRepository provides basic CRUD operations (save, findById, findAll, deleteById) out of the box.
    // Custom query methods can be defined here if needed.
}
