package com.example.demo.repository;

import com.example.demo.model.entity.Document;
import com.example.demo.model.enums.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCompanyId(Long companyId);

    List<Document> findByCompanyIdAndFiscalYear(Long companyId, Integer fiscalYear);

    List<Document> findByStatus(DocumentStatus status);

    List<Document> findByCompanyIdAndStatus(Long companyId, DocumentStatus status);
}
