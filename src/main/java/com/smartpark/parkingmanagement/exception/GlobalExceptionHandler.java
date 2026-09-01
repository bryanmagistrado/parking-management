package com.smartpark.parkingmanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartpark.parkingmanagement.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles DTO Validation Failures (e.g., @NotBlank, @Min, @Pattern) Maps field
	 * names directly to their specific custom validation messages.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ErrorResponseDto errorPayload = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), "Validation failed for incoming request payload.", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorPayload);
	}

	/**
	 * Handles Invalid Inputs & Bad Arguments
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
		ErrorResponseDto errorPayload = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorPayload);
	}

	/**
	 * Handles State Conflicts (e.g., "Parking lot is full", "Vehicle already
	 * checked in")
	 */
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalStateException(IllegalStateException ex) {
		ErrorResponseDto errorPayload = new ErrorResponseDto(HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorPayload);
	}

	/**
	 * Catch-All for unexpected runtime system failures
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
		ErrorResponseDto errorPayload = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				"An unexpected internal error occurred on the server.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorPayload);
	}

}
