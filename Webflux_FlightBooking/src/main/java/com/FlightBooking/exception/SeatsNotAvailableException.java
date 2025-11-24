package com.FlightBooking.exception;

@SuppressWarnings("serial")
public class SeatsNotAvailableException extends RuntimeException {
	public SeatsNotAvailableException(String message) {
		super(message);
	}
}
