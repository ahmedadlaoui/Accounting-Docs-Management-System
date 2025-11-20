package com.example.demo.dto;

import com.example.demo.model.enums.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreateDTO {

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @NotNull(message = "Document type is required")
    private DocumentType documentType;

    @NotBlank(message = "Accounting category is required")
    private String accountingCategory;

    @NotNull(message = "Document date is required")
    private LocalDate documentDate;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Supplier is required")
    private String supplier;

    @NotNull(message = "Fiscal year is required")
    private Integer fiscalYear;
}
