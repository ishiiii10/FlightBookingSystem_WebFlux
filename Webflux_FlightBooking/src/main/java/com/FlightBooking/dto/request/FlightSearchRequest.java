package com.FlightBooking.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

import com.FlightBooking.enums.Cities;
import com.FlightBooking.enums.TripType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class FlightSearchRequest {

	@NotNull(message = "Source city is required")
	private Cities fromCity;

	@NotNull(message = "Destination city is required")
	private Cities toCity;

	@NotNull(message = "Journey date is required")
	@FutureOrPresent(message = "Travel date cannot be in the past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	@NotNull(message = "Trip type is required")
	private TripType tripType;

	// only for round trip
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnDate;
}
