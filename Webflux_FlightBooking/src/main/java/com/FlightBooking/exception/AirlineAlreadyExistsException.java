package com.FlightBooking.exception;

@SuppressWarnings("serial")
public class AirlineAlreadyExistsException extends RuntimeException {

	public AirlineAlreadyExistsException(String message) {
		super(message);
	}
}
