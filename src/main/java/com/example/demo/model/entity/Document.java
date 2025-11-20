package com.example.demo.model.entity;

import com.example.demo.model.enums.DocumentStatus;
import com.example.demo.model.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Document number is required")
    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @NotNull(message = "Document type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @NotBlank(message = "Accounting category is required")
    @Column(name = "accounting_category", nullable = false)
    private String accountingCategory;

    @NotNull(message = "Document date is required")
    @Column(name = "document_date", nullable = false)
    private LocalDate documentDate;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Supplier is required")
    @Column(name = "supplier", nullable = false)
    private String supplier;

    @NotBlank(message = "File path is required")
    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status = DocumentStatus.EN_ATTENTE;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @Column(name = "accountant_comment", columnDefinition = "TEXT")
    private String accountantComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validated_by")
    private User validatedBy; // Accountant who validated/rejected

    @NotNull(message = "Fiscal year is required")
    @Column(name = "fiscal_year", nullable = false)
    private Integer fiscalYear;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
