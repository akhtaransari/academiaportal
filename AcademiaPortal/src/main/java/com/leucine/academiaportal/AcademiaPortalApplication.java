package com.leucine.academiaportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the AcademiaPortal application.
 * This class contains the main method which is used to run the Spring Boot application.
 */
@SpringBootApplication
public class AcademiaPortalApplication {

	/**
	 * The main method which starts the Spring Boot application.
	 * It delegates to Spring Boot's {@link SpringApplication#run(Class, String...)} method
	 * to launch the application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AcademiaPortalApplication.class, args);
	}
}
