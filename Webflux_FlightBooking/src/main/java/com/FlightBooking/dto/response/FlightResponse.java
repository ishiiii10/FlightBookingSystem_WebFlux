package com.FlightBooking.dto.response;




import lombok.Data;

import java.time.LocalDateTime;

import com.FlightBooking.enums.Cities;

@Data
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

    private float price;

    private int totalSeats;
    private int availableSeats;
}