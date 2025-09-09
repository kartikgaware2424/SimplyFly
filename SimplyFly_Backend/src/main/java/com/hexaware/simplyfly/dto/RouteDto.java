package com.hexaware.simplyfly.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {


    @Size(max = 100)
    private String origin;

   
    @Size(max = 100)
    private String destination;

    @Positive
    private Double distanceInKm;

  
    @Pattern(regexp = "^[0-9]+h\\s[0-9]+m$", message = "Travel duration must be in format like '2h 15m'")
    private String travelDuration;
}
