package com.FlightBooking.dto.request;


import com.FlightBooking.enums.Gender;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PassengerRequest {

    @NotBlank(message = "Passenger name is required")
    private String name;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 120, message = "Age seems invalid")
    private Integer age;

    @NotBlank(message = "Passenger email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String contactNumber;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;       // e.g. "12A"
}
