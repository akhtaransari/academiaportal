package com.leucine.academiaportal.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for the Academia Portal application.
 * This class is responsible for handling exceptions thrown across the application
 * and providing a structured error response.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * Handles exceptions when no handler is found for a request.
	 *
	 * @param ex the exception thrown
	 * @param wr the web request context
	 * @return a ResponseEntity with ErrorDetails and HTTP status BAD_REQUEST
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandler(NoHandlerFoundException ex, WebRequest wr) {
		log.warn("NoHandlerFoundException: " + ex.getMessage(), ex);
		return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles generic exceptions.
	 *
	 * @param ex the exception thrown
	 * @param wr the web request context
	 * @return a ResponseEntity with ErrorDetails and HTTP status BAD_REQUEST
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> parentException(Exception ex, WebRequest wr) {
		log.warn("Exception: " + ex.getMessage(), ex);
		return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles validation exceptions for method arguments.
	 *
	 * @param ex the exception thrown
	 * @param wr the web request context
	 * @return a ResponseEntity with ErrorDetails and HTTP status BAD_REQUEST
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgValidException(MethodArgumentNotValidException ex, WebRequest wr) {
		log.warn("MethodArgumentNotValidException: " + ex.getMessage(), ex);
		return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles exceptions specific to the AcademiaPortalApplication.
	 *
	 * @param ex the AcademiaPortalApplication exception thrown
	 * @param wr the web request context
	 * @return a ResponseEntity with ErrorDetails and HTTP status BAD_REQUEST
	 */
	@ExceptionHandler(AcademiaPortalException.class)
	public ResponseEntity<ErrorDetails> gymException(AcademiaPortalException ex, WebRequest wr) {
		log.warn("AcademiaPortalApplication: " + ex.getMessage(), ex);
		return new ResponseEntity<>(new ErrorDetails(ex.getMessage(), wr.getDescription(false), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}
}
