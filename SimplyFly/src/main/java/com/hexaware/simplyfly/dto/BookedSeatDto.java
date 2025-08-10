package com.hexaware.simplyfly.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class BookedSeatDto {
	@NotNull
    @Min(1)
    
    private int bookingId;

    @NotNull
    @Min(1)
   
    private int seatId;

    @NotNull
    @Min(1)
   
    private int passengerId;

    @Positive
    @Max(1000000) // Example: max price is 1 million
    private double price;
}