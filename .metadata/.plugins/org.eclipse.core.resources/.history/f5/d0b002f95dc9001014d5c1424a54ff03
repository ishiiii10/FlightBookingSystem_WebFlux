package com.FlightBooking.service;



import com.FlightBooking.dto.request.BookingRequest;
import com.FlightBooking.dto.response.BookingResponse;
import com.FlightBooking.dto.response.TicketDetailResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {

    Mono<BookingResponse> bookTicket(String flightId, BookingRequest request);

    Mono<TicketDetailResponse> getTicketDetails(String pnr);

    Flux<TicketDetailResponse> getBookingHistory(String emailId);

    Mono<BookingResponse> cancelBooking(String pnr);
}
