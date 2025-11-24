package com.FlightBooking.service;




import com.FlightBooking.dto.request.FlightRequest;
import com.FlightBooking.dto.request.FlightSearchRequest;
import com.FlightBooking.dto.response.FlightResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

    Mono<FlightResponse> addFlightToInventory(FlightRequest request);

    Flux<FlightResponse> searchFlights(FlightSearchRequest request);
}