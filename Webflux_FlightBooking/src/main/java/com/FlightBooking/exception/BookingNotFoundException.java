package com.FlightBooking.exception;

@SuppressWarnings("serial")
public class BookingNotFoundException extends RuntimeException {
	public BookingNotFoundException(String message) {
		super(message);
	}
}
