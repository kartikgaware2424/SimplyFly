package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

	 @NotBlank
	    @Size(max = 50)
	    private String documentType;

	    @NotBlank
	    @Size(max = 100)
	    private String documentNumber;

	    @NotNull
	    @Min(1)
	    @Max(Integer.MAX_VALUE)
	    private int userId;
}
