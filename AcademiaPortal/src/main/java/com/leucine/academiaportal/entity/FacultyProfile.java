package com.leucine.academiaportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a faculty profile.
 * This entity is mapped to the 'faculty_profile' table in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyProfile {

    /**
     * Primary key for the FacultyProfile entity.
     * Automatically generated by the database and corresponds to the 'user_id' column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * Reference to the User entity.
     * Establishes a one-to-one relationship where each faculty profile is associated with a single user.
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * URL or path to the faculty member's photo.
     */
    private String photo;

    /**
     * Reference to the Department entity.
     * Establishes a many-to-one relationship where multiple faculty profiles can be associated with one department.
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * The office hours of the faculty member.
     * Stores the time during which the faculty member is available for office visits.
     */
    private String officeHours;
}
