package com.hexaware.simplyfly.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FlightDto {
	 
	    @Size(max = 100)
	    private String flightName;

	   
	    @Size(max = 20)
	    private String flightNumber;

	    
	    @Min(1)
	    @Max(1000)
	    private Integer totalSeats;

	   
	    @Positive
	    @Max(100000)
	    private Double fare;

	    
	    @Size(max = 20)
	    private String baggageCheckIn;

	   
	    @Size(max = 20)
	    private String baggageCabin;

	    
	    @FutureOrPresent
	    private LocalDate departureDate;

	   
	    @FutureOrPresent
	    private LocalDateTime departureTime;

	   
	    @FutureOrPresent
	    private LocalDateTime arrivalTime;

	   
	   
	    private Integer routeId;

	   
	   
	    private Integer ownerId;
}