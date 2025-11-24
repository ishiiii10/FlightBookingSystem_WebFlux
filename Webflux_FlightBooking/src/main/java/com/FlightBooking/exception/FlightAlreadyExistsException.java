package com.FlightBooking.exception;

@SuppressWarnings("serial")
public class FlightAlreadyExistsException extends RuntimeException {
	public FlightAlreadyExistsException(String message) {
		super(message);
	}
}
