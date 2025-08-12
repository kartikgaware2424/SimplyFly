package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class PaymentDto {

	
    @Positive
    private Double amount;

    
    @PastOrPresent
    private LocalDateTime paymentDate;

    
    @Pattern(regexp = "CARD|UPI|NETBANKING", message = "Payment method must be CARD, UPI, or NETBANKING")
    private String paymentMethod;

   
    @Size(max = 50)
    private String transactionId;

   
    @Pattern(regexp = "SUCCESS|FAILED", message = "Status must be SUCCESS or FAILED")
    private String status;

   
    private Integer bookingId;
}