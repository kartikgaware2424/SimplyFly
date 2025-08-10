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

	@NotNull
    @Positive
    private Double amount;

    @NotNull
    @PastOrPresent
    private LocalDateTime paymentDate;

    @NotNull
    @Pattern(regexp = "CARD|UPI|NETBANKING", message = "Payment method must be CARD, UPI, or NETBANKING")
    private String paymentMethod;

    @NotBlank
    @Size(max = 50)
    private String transactionId;

    @NotNull
    @Pattern(regexp = "SUCCESS|FAILED", message = "Status must be SUCCESS or FAILED")
    private String status;

    @NotNull
    @Min(1)
    private Integer bookingId;
}