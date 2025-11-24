package com.FlightBooking.dto.response;

import com.FlightBooking.enums.BookingStatus;
import com.FlightBooking.enums.Cities;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDetailResponse {

    private String pnr;
    private BookingStatus status;

    private String flightCode;
    private String airlineName;

    private Cities fromCity;
    private Cities toCity;

    private LocalDateTime departureTime;
    private LocalDate journeyDate;
    private LocalDate returnDate;

    private Integer numberOfSeats;

    private List<PassengerResponse> passengers;
}