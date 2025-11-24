package com.FlightBooking.service;

import com.FlightBooking.dto.request.BookingRequest;
import com.FlightBooking.dto.request.PassengerRequest;
import com.FlightBooking.dto.response.BookingResponse;
import com.FlightBooking.entity.Booking;
import com.FlightBooking.entity.FlightInventory;

import com.FlightBooking.enums.BookingStatus;
import com.FlightBooking.enums.Cities;
import com.FlightBooking.enums.Gender;
import com.FlightBooking.enums.MealType;
import com.FlightBooking.enums.TripType;
import com.FlightBooking.exception.CancellationNotAllowedException;
import com.FlightBooking.exception.SeatsNotAvailableException;
import com.FlightBooking.repository.AirlineRepository;
import com.FlightBooking.repository.BookingRepository;
import com.FlightBooking.repository.FlightInventoryRepository;
import com.FlightBooking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookingServiceTest {

	@Mock
	private BookingRepository bookingRepo;

	@Mock
	private FlightInventoryRepository flightRepo;

	@Mock
	private AirlineRepository airlineRepo;

	@InjectMocks
	private BookingServiceImpl bookingService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	private BookingRequest buildBookingRequest() {
		BookingRequest req = new BookingRequest();
		req.setUserEmail("user@example.com");
		req.setTripType(TripType.ONE_WAY);
		req.setMealType(MealType.VEG);
		req.setJourneyDate(LocalDate.now().plusDays(5));
		req.setReturnDate(null);
		req.setNumberOfSeats(2);

		PassengerRequest p1 = new PassengerRequest();
		p1.setName("Amit");
		p1.setAge(25);
		p1.setGender(Gender.MALE);
		p1.setEmail("amit@example.com");
		p1.setContactNumber("9876543210");
		p1.setSeatNumber("12A");

		PassengerRequest p2 = new PassengerRequest();
		p2.setName("Riya");
		p2.setAge(24);
		p2.setGender(Gender.FEMALE);
		p2.setEmail("riya@example.com");
		p2.setContactNumber("9876543211");
		p2.setSeatNumber("12B");

		req.setPassengers(List.of(p1, p2));
		return req;
	}

	private FlightInventory buildFlightWithSeats(int availableSeats) {
		FlightInventory fi = new FlightInventory();
		fi.setId("FLIGHT_ID");
		fi.setAirlineId("AIRLINE_ID");
		fi.setAirlineCode("AI");
		fi.setAirlineName("AirIndia");
		fi.setFlightCode("AI201");
		fi.setFromCity(Cities.DELHI);
		fi.setToCity(Cities.MUMBAI);
		fi.setDepartureTime(LocalDateTime.now().plusDays(5).withHour(10).withMinute(0));
		fi.setPrice(5500f);
		fi.setTotalSeats(120);
		fi.setAvailableSeats(availableSeats);
		return fi;
	}

	@Test
	void bookTicket_success() {
		BookingRequest req = buildBookingRequest();
		FlightInventory flight = buildFlightWithSeats(50);

		Booking saved = new Booking();
		saved.setPnr("ABC12345");
		saved.setFlightId("FLIGHT_ID");
		saved.setStatus(BookingStatus.BOOKED);
		saved.setNumberOfSeats(req.getNumberOfSeats());

		when(flightRepo.findById("FLIGHT_ID")).thenReturn(Mono.just(flight));
		when(flightRepo.save(any(FlightInventory.class))).thenReturn(Mono.just(flight));
		when(bookingRepo.save(any(Booking.class))).thenReturn(Mono.just(saved));

		Mono<BookingResponse> result = bookingService.bookTicket("FLIGHT_ID", req);

		StepVerifier.create(result)
				.expectNextMatches(resp -> "ABC12345".equals(resp.getPnr()) && resp.getStatus() == BookingStatus.BOOKED)
				.verifyComplete();
	}

	@Test
	void bookTicket_notEnoughSeats_throwsException() {
		BookingRequest req = buildBookingRequest();
		FlightInventory flight = buildFlightWithSeats(1); // only 1 seat

		when(flightRepo.findById("FLIGHT_ID")).thenReturn(Mono.just(flight));

		Mono<BookingResponse> result = bookingService.bookTicket("FLIGHT_ID", req);

		StepVerifier.create(result).expectError(SeatsNotAvailableException.class).verify();
	}

	@Test
	void cancelBooking_within24HoursNotAllowed() {
		// booking with flight departing in < 24 hours
		Booking booking = new Booking();
		booking.setPnr("ABC12345");
		booking.setFlightId("FLIGHT_ID");
		booking.setStatus(BookingStatus.BOOKED);
		booking.setNumberOfSeats(2);
		booking.setJourneyDate(LocalDate.now().plusDays(1));

		FlightInventory flight = new FlightInventory();
		flight.setId("FLIGHT_ID");
		flight.setDepartureTime(LocalDateTime.now().plusHours(10)); // < 24h
		flight.setAvailableSeats(50);
		flight.setTotalSeats(120);

		when(bookingRepo.findByPnr("ABC12345")).thenReturn(Mono.just(booking));
		when(flightRepo.findById("FLIGHT_ID")).thenReturn(Mono.just(flight));

		Mono<BookingResponse> result = bookingService.cancelBooking("ABC12345");

		StepVerifier.create(result).expectError(CancellationNotAllowedException.class).verify();
	}
}
