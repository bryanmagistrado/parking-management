package com.smartpark.parkingmanagement.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponseDto {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private Map<String, String> validations; // Holds field-specific errors

	public ErrorResponseDto(int status, String error, String message) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public ErrorResponseDto(int status, String error, String message, Map<String, String> validations) {
		this(status, error, message);
		this.validations = validations;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getValidations() {
		return validations;
	}

}
