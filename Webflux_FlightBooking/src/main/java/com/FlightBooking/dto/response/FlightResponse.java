package com.FlightBooking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

import com.FlightBooking.enums.Cities;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightResponse {

	private String id;

	private String airlineId;
	private String airlineCode;
	private String airlineName;
	private String airlineLogoUrl;

	private String flightCode;

	private Cities fromCity;
	private Cities toCity;

	private LocalDateTime departureTime;

	private Float price;

	private Integer totalSeats;
	private Integer availableSeats;
}