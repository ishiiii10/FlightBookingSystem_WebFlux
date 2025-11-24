package com.FlightBooking.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AirlineRequest {

	@NotBlank(message = "Airline code is required")
	@Size(min = 2, max = 5, message = "Airline code must be between 2 to 5 characters")
	private String airlineCode; // e.g. "AI", "6E"

	@NotBlank(message = "Airline name is required")
	private String name;

	private String logoUrl;

	@NotBlank(message = "Airline email is required")
	@Email(message = "Invalid airline email")
	private String email;

	private boolean active = true;
}