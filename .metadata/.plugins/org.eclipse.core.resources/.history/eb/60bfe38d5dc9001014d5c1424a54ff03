package com.FlightBooking.entity;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "airlines")
public class Airline {

    @Id
    private String id; 
    
    @Indexed(unique = true)
    private String airlineCode;

    private String name;        
    private String logoUrl; 
    private String email;
    
    private boolean active=true;
    
}