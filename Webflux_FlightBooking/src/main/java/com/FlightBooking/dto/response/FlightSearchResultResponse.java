package com.FlightBooking.dto.response;


import lombok.Data;

import java.util.List;

import com.FlightBooking.enums.TripType;

@Data
public class FlightSearchResultResponse {

    private TripType tripType;                // ONE_WAY / ROUND_TRIP
    private List<FlightResponse> onwardFlights;
    private List<FlightResponse> returnFlights;   // null / empty for ONE_WAY
}
