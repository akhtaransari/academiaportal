package com.leucine.academiaportal.exception;

/**
 * Custom exception class for the Academia Portal application.
 * This exception extends RuntimeException and is used to handle specific errors
 * related to the application domain.
 */
public class AcademiaPortalException extends RuntimeException {

	/**
	 * Constructs a new AcademiaPortalApplication exception with the specified detail message.
	 *
	 * @param message the detail message which is saved for later retrieval by the {@link #getMessage()} method
	 */
	public AcademiaPortalException(String message) {
		super(message);
	}
}
