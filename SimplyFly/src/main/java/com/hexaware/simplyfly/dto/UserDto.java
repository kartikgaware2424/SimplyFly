package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @NotBlank
    @Size(min = 8, max = 64)
    private String password;
    
    @Min(1)
    private int age;

    @NotNull
    private UserRoleDto role;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be a valid 10-digit number")
    private String contactNumber;

    @NotBlank
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank
    @Size(max = 255)
    private String address;
}
