package com.FlightBooking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.FlightBooking.dto.request.FlightRequest;
import com.FlightBooking.dto.request.FlightSearchRequest;
import com.FlightBooking.dto.response.FlightResponse;
import com.FlightBooking.dto.response.FlightSearchResultResponse;
import com.FlightBooking.entity.FlightInventory;
import com.FlightBooking.enums.Cities;
import com.FlightBooking.enums.TripType;
import com.FlightBooking.exception.FlightAlreadyExistsException;
import com.FlightBooking.repository.FlightInventoryRepository;
import com.FlightBooking.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

	private final FlightInventoryRepository flightRepo;

	@Override
	public Mono<FlightResponse> addFlightToInventory(FlightRequest request) {

		if (request.getFromCity() == request.getToCity()) {
			return Mono.error(new IllegalArgumentException("Source and destination cannot be same"));
		}

		if (request.getTotalSeats() < 1) {
			return Mono.error(new IllegalArgumentException("Total seats must be at least 1"));
		}

		// Duplicate check by flightCode
		return flightRepo.existsByFlightCode(request.getFlightCode()).flatMap(exists -> {
			if (exists) {
				return Mono.error(new FlightAlreadyExistsException(
						"Flight with code " + request.getFlightCode() + " already exists"));
			}

			FlightInventory fi = new FlightInventory();
			fi.setAirlineId(request.getAirlineId());
			fi.setAirlineCode(request.getAirlineCode());
			fi.setAirlineName(request.getAirlineName());
			fi.setAirlineLogoUrl(request.getAirlineLogoUrl());
			fi.setFlightCode(request.getFlightCode());
			fi.setFromCity(request.getFromCity());
			fi.setToCity(request.getToCity());
			fi.setDepartureTime(request.getDepartureTime());
			fi.setPrice(request.getPrice());
			fi.setTotalSeats(request.getTotalSeats());
			fi.setAvailableSeats(request.getTotalSeats());

			return flightRepo.save(fi).map(this::toResponseForCreate);
		});
	}

	@Override
	public Mono<FlightSearchResultResponse> searchFlights(FlightSearchRequest request) {

		if (request.getFromCity() == request.getToCity()) {
			return Mono.error(new IllegalArgumentException("Source and destination cannot be same"));
		}

		if (request.getTripType() == null) {
			return Mono.error(new IllegalArgumentException("Trip type is required"));
		}

		// ONE_WAY search
		if (request.getTripType().name().equals("ONE_WAY")) {

			var date = request.getJourneyDate();
			var startOfDay = date.atStartOfDay();
			var endOfDay = date.plusDays(1).atStartOfDay();

			return flightRepo.findByFromCityAndToCityAndDepartureTimeBetween(request.getFromCity(), request.getToCity(),
					startOfDay, endOfDay).map(this::toFullResponse).collectList().map(list -> {
						FlightSearchResultResponse resp = new FlightSearchResultResponse();
						resp.setTripType(request.getTripType());
						resp.setOnwardFlights(list);
						resp.setReturnFlights(null);
						return resp;
					});
		}

		// ROUND_TRIP search
		// validate return date
		if (request.getReturnDate() == null) {
			return Mono.error(new IllegalArgumentException("Return date is required for round trip"));
		}
		if (!request.getReturnDate().isAfter(request.getJourneyDate())) {
			return Mono.error(new IllegalArgumentException("Return date must be after journey date"));
		}

		// onward leg
		var onwardStart = request.getJourneyDate().atStartOfDay();
		var onwardEnd = request.getJourneyDate().plusDays(1).atStartOfDay();

		// return leg (reverse route)
		var returnStart = request.getReturnDate().atStartOfDay();
		var returnEnd = request.getReturnDate().plusDays(1).atStartOfDay();

		var onwardFlux = flightRepo.findByFromCityAndToCityAndDepartureTimeBetween(request.getFromCity(),
				request.getToCity(), onwardStart, onwardEnd).map(this::toFullResponse).collectList();

		var returnFlux = flightRepo.findByFromCityAndToCityAndDepartureTimeBetween(request.getToCity(), // swapped
				request.getFromCity(), returnStart, returnEnd).map(this::toFullResponse).collectList();

		return Mono.zip(onwardFlux, returnFlux).map(tuple -> {
			FlightSearchResultResponse resp = new FlightSearchResultResponse();
			resp.setTripType(request.getTripType());
			resp.setOnwardFlights(tuple.getT1());
			resp.setReturnFlights(tuple.getT2());
			return resp;
		});
	}

	// ---- mapping helpers ----

	private FlightResponse toResponseForCreate(FlightInventory fi) {
		FlightResponse resp = new FlightResponse();
		resp.setId(fi.getId());
		resp.setFlightCode(fi.getFlightCode());
		// DO NOT set price / seats / anything else here
		return resp;
	}

	private FlightResponse toFullResponse(FlightInventory fi) {
		FlightResponse resp = new FlightResponse();
		resp.setId(fi.getId());
		resp.setAirlineId(fi.getAirlineId());
		resp.setAirlineCode(fi.getAirlineCode());
		resp.setAirlineName(fi.getAirlineName());
		resp.setAirlineLogoUrl(fi.getAirlineLogoUrl());
		resp.setFlightCode(fi.getFlightCode());
		resp.setFromCity(fi.getFromCity());
		resp.setToCity(fi.getToCity());
		resp.setDepartureTime(fi.getDepartureTime());
		resp.setPrice(fi.getPrice());
		resp.setTotalSeats(fi.getTotalSeats());
		resp.setAvailableSeats(fi.getAvailableSeats());
		return resp;
	}
}