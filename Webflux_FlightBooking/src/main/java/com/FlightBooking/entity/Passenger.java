package com.FlightBooking.entity;

import com.FlightBooking.enums.Gender;

import lombok.Data;

@Data
public class Passenger {
	

	    private String name;
	    private Gender gender;        // enum: MALE / FEMALE / OTHER
	    private Integer age;

	    private String email;         // Passenger email (can be same or different from userEmail)
	    private String bookingId;     // PNR (we'll set this from Booking.pnr)
	    private String seatNumber;    // e.g. "12A"
	    private String contactNumber; // String to handle +91, leading zeros, etc.
	}


