package com.FlightBooking.dto.request;




import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

import com.FlightBooking.enums.Cities;

@Data
public class FlightRequest {

    @NotBlank(message = "Airline ID is required")
    private String airlineId;

    @NotBlank(message = "Airline code is required")
    private String airlineCode;

    @NotBlank(message = "Airline name is required")
    private String airlineName;

    private String airlineLogoUrl; 
    
    @NotBlank(message = "Flight code is required")
    @Size(max = 10, message = "Flight code must be at most 10 characters")
    private String flightCode;        // NEW


    @NotNull(message = "Source city is required")
    private Cities fromCity;

    @NotNull(message = "Destination city is required")
    private Cities toCity;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @Positive(message = "Price must be greater than 0")
    private float price;

    @Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;
}