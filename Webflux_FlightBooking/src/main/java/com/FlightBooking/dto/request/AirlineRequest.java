package com.FlightBooking.dto.request;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AirlineRequest {

    @NotBlank(message = "Airline code is required")
    @Size(max = 10, message = "Airline code must be at most 10 characters")
    private String airlineCode;    // e.g. "AI", "6E"

    @NotBlank(message = "Airline name is required")
    private String name;

    
    private String logoUrl;

    @NotBlank(message = "Airline email is required")
    @Email(message = "Invalid airline email")
    private String email;

    
    private boolean active = true;
}