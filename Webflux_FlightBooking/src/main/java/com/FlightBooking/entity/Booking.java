package com.FlightBooking.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.FlightBooking.enums.BookingStatus;
import com.FlightBooking.enums.MealType;
import com.FlightBooking.enums.TripType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "bookings")
public class Booking {

	@Id
	private String pnr;

	private String flightId;
	private String userEmail;

	private TripType tripType;
	private MealType mealType;
	private BookingStatus status;

	private LocalDate journeyDate;
	private LocalDate returnDate;

	private int numberOfSeats;
	private List<Passenger> passengers;

	private LocalDateTime bookingDateTime;
}