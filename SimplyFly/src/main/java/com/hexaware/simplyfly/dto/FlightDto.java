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
	  @NotBlank
	    @Size(max = 100)
	    private String flightName;

	    @NotBlank
	    @Size(max = 20)
	    private String flightNumber;

	    @NotNull
	    @Min(1)
	    @Max(1000)
	    private Integer totalSeats;

	    @NotNull
	    @Positive
	    @Max(100000)
	    private Double fare;

	    @NotBlank
	    @Size(max = 20)
	    private String baggageCheckIn;

	    @NotBlank
	    @Size(max = 20)
	    private String baggageCabin;

	    @NotNull
	    @FutureOrPresent
	    private LocalDate departureDate;

	    @NotNull
	    @FutureOrPresent
	    private LocalDateTime departureTime;

	    @NotNull
	    @FutureOrPresent
	    private LocalDateTime arrivalTime;

	    @NotNull
	    @Min(1)
	    private Integer routeId;

	    @NotNull
	    @Min(1)
	    private Integer ownerId;
}