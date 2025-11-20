package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private Long id;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "ICE is required")
    @Pattern(regexp = "\\d{15}", message = "ICE must be 15 digits")
    private String ice;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^(\\+212|0)[5-7]\\d{8}$", message = "Invalid Moroccan phone number")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String contactEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
