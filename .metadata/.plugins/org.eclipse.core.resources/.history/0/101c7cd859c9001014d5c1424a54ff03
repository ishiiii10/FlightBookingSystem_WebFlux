package com.FlightBooking.dto.response;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.FlightBooking.enums.BookingStatus;
import com.FlightBooking.enums.Cities;
import com.FlightBooking.enums.MealType;
import com.FlightBooking.enums.TripType;

@Data
public class TicketDetailResponse {

    private String pnr;
    private BookingStatus status;

    // Flight + airline info
    private String flightId;
    private String airlineCode;
    private String airlineName;
    private String airlineLogoUrl;

    private Cities fromCity;
    private Cities toCity;

    private LocalDate journeyDate;
    private LocalDate returnDate;        

    private LocalDateTime departureTime;
    private TripType tripType;
    private MealType mealType;

    private float price;
    private int numberOfSeats;

    
    private List<PassengerResponse> passengers;
}