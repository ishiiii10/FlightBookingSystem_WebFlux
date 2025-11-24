package com.FlightBooking.service;

import com.FlightBooking.dto.request.FlightRequest;
import com.FlightBooking.dto.request.FlightSearchRequest;
import com.FlightBooking.dto.response.FlightResponse;
import com.FlightBooking.dto.response.FlightSearchResultResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

	Mono<FlightResponse> addFlightToInventory(FlightRequest request);

	Mono<FlightSearchResultResponse> searchFlights(FlightSearchRequest request);
}