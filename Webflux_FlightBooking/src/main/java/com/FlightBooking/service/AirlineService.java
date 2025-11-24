package com.FlightBooking.service;

import com.FlightBooking.dto.request.AirlineRequest;
import com.FlightBooking.dto.response.AirlineResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirlineService {

	Mono<AirlineResponse> createAirline(AirlineRequest request);

	Flux<AirlineResponse> getAllAirlines();
}