package com.FlightBooking.entity;

import java.time.LocalDateTime;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.FlightBooking.enums.Cities;

import jakarta.validation.constraints.Min;

@Data
@Document(collection="flight_inventory")
public class FlightInventory {
	@Id
	public String id;
	private String airlineId;
	private String airlineCode;
	private String airlineName;       
    private String airlineLogoUrl;   
    
    private String flightCode; 

    private Cities fromCity;
    private Cities toCity;

    private LocalDateTime departureTime;

    private float price;              
    
    
    private int totalSeats;
    
    
    private int availableSeats;

}
