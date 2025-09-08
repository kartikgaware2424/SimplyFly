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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto {

	  @NotNull
	    @Positive
	    private Double amount;

	    @NotNull
	    @PastOrPresent
	    private LocalDateTime refundDate;

	    @NotBlank
	    @Pattern(regexp = "UPI|BANK_TRANSFER", message = "Refund method must be UPI or BANK_TRANSFER")
	    private String refundMethod;

	    @NotBlank
	    @Size(max = 50)
	    private String transactionId;

	    @NotBlank
	    @Size(max = 200)
	    private String reason;

	    @NotNull
	    @Pattern(regexp = "PENDING|COMPLETED|FAILED", message = "Status must be PENDING, COMPLETED, or FAILED")
	    private String status;

	    @NotNull
	    @Min(1)
	    private Integer userId;

	    @NotNull
	    @Min(1)
	    private Integer bookingId;
}
