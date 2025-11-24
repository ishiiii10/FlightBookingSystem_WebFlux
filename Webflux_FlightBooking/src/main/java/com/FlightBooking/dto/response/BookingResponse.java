package com.FlightBooking.dto.response;

import com.FlightBooking.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

	private String pnr;
	private BookingStatus status;

}
