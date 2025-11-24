package com.FlightBooking.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.FlightBooking.entity.Airline;

import reactor.core.publisher.Mono;

@Repository
public interface AirlineRepository extends ReactiveMongoRepository<Airline, String> {
	Mono<Airline> findByAirlineCode(String airlineCode);
	
   
}