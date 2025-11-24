package com.FlightBooking.dto.response;


import com.FlightBooking.enums.Gender;

import lombok.Data;

@Data
public class PassengerResponse {

    private String name;
    private Gender gender;
    private Integer age;

    private String email;
    private String contactNumber;
    private String seatNumber;
}
