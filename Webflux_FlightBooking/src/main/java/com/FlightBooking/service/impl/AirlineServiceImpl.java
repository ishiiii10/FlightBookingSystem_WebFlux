package com.FlightBooking.service.impl;




import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.FlightBooking.dto.request.AirlineRequest;
import com.FlightBooking.dto.response.AirlineResponse;
import com.FlightBooking.entity.Airline;
import com.FlightBooking.exception.AirlineAlreadyExistsException;
import com.FlightBooking.repository.AirlineRepository;
import com.FlightBooking.service.AirlineService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Mono<AirlineResponse> createAirline(AirlineRequest request) {

        String code = request.getAirlineCode();

        return airlineRepository.existsByAirlineCode(code)
                .flatMap(exists -> {
                    if (exists) {
                        // airline already present -> throw our custom exception
                        return Mono.error(new AirlineAlreadyExistsException(
                                "Airline with code " + code + " already exists"));
                    }

                    // otherwise create new airline
                    Airline airline = new Airline();
                    airline.setAirlineCode(request.getAirlineCode());
                    airline.setName(request.getName());
                    airline.setLogoUrl(request.getLogoUrl());
                    airline.setEmail(request.getEmail());
                    airline.setActive(true); // ensure active

                    return airlineRepository.save(airline)
                            .map(saved -> {
                                AirlineResponse resp = new AirlineResponse();
                                resp.setId(saved.getId());
                                resp.setAirlineCode(saved.getAirlineCode());
                                // only id + code; others left null (hidden by @JsonInclude)
                                return resp;
                            });
                });
    }

    @Override
    public Flux<AirlineResponse> getAllAirlines() {
        return airlineRepository.findAll()
                .map(this::toResponse);
    }

    private AirlineResponse toResponse(Airline airline) {
        AirlineResponse resp = new AirlineResponse();
        resp.setId(airline.getId());
        resp.setAirlineCode(airline.getAirlineCode());
        resp.setName(airline.getName());
        resp.setLogoUrl(airline.getLogoUrl());
        resp.setEmail(airline.getEmail());
        resp.setActive(airline.isActive());
        return resp;
    }
}
