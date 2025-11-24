package com.FlightBooking.dto.response;


import com.FlightBooking.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingResponse {

    private String pnr;
    private BookingStatus status;
    private String message;
}
