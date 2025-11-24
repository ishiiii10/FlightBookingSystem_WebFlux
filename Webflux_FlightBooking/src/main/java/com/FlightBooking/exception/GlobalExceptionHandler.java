package com.FlightBooking.exception;

import com.FlightBooking.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 1) @Valid validation errors (WebFlux)
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<Map<String, String>> handleWebFluxValidationErrors(WebExchangeBindException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	// 2) (Optional safety) MethodArgumentNotValid for MVC-style
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	// 3) Airline already exists
	@ExceptionHandler(AirlineAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAirlineExists(AirlineAlreadyExistsException ex) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(FlightAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleFlightExists(FlightAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT) // 409
				.body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFlightNotFound(FlightNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookingNotFound(BookingNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(SeatsNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleSeatsNotAvailable(SeatsNotAvailableException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT) // state conflict: not enough seats
				.body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(CancellationNotAllowedException.class)
	public ResponseEntity<ErrorResponse> handleCancellationNotAllowed(CancellationNotAllowedException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT) // conflict: booking cannot be cancelled
				.body(new ErrorResponse(ex.getMessage()));
	}

	// 4) Generic business logic errors (e.g. source == destination later)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
	}
}