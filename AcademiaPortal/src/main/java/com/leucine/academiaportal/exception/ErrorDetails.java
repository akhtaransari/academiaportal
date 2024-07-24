package com.leucine.academiaportal.exception;

import java.time.LocalDateTime;

/**
 * A record class to encapsulate error details for the Academia Portal application.
 * Records are a special kind of class in Java that are used for immutable data carriers.
 * This class holds information about errors including a message, a description, and a timestamp.
 *
 * @param message      A brief message describing the error.
 * @param description  A detailed description of the error, often including the request URI or context.
 * @param timeStamp    The timestamp when the error occurred.
 */
public record ErrorDetails(
		String message,
		String description,
		LocalDateTime timeStamp
) {}
