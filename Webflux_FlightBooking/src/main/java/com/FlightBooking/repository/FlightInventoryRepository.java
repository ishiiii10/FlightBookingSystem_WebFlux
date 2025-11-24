package com.FlightBooking.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.FlightBooking.entity.FlightInventory;
import com.FlightBooking.enums.Cities;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface FlightInventoryRepository extends ReactiveMongoRepository<FlightInventory, String> {

	Flux<FlightInventory> findByFromCityAndToCityAndDepartureTimeBetween(Cities fromCity, Cities toCity,
			LocalDateTime start, LocalDateTime end);

	Mono<Boolean> existsByFlightCode(String flightCode);
}
