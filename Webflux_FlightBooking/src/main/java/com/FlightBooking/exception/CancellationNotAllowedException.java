package com.FlightBooking.exception;

@SuppressWarnings("serial")
public class CancellationNotAllowedException extends RuntimeException {
	public CancellationNotAllowedException(String message) {
		super(message);
	}
}
