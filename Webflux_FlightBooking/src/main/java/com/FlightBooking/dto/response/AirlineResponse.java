package com.FlightBooking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirlineResponse {

	private String id;
	private String airlineCode;

	private String name;
	private String logoUrl;
	private String email;
	private boolean active = true;

}