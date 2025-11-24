package com.FlightBooking.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.FlightBooking.enums.MealType;
import com.FlightBooking.enums.TripType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class BookingRequest {

    @NotBlank(message = "User email is required")
    @Email(message = "Invalid user email")
    private String userEmail;

    @NotNull(message = "Trip type is required")
    private TripType tripType;

    @NotNull(message = "Meal type is required")
    private MealType mealType;

    @NotNull(message = "Journey date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journeyDate;

    // For ROUND_TRIP; logic validation later
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @Min(value = 1, message = "At least one seat must be booked")
    private int numberOfSeats;

    @NotEmpty(message = "At least one passenger is required")
    private List<@Valid PassengerRequest> passengers;
}
