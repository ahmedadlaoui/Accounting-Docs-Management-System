package com.example.demo.service;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.model.entity.Company;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDTO> getAllCompanies() {
        log.info("Fetching all companies");
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(Long id) {
        log.info("Fetching company with id: {}", id);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return companyMapper.toDTO(company);
    }

    @Transactional
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        log.info("Creating new company with ICE: {}", companyDTO.getIce());

        if (companyRepository.existsByIce(companyDTO.getIce())) {
            throw new DuplicateResourceException("Company with ICE " + companyDTO.getIce() + " already exists");
        }

        Company company = companyMapper.toEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        log.info("Company created successfully with id: {}", savedCompany.getId());
        return companyMapper.toDTO(savedCompany);
    }

    @Transactional
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        log.info("Updating company with id: {}", id);

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        if (!company.getIce().equals(companyDTO.getIce()) && companyRepository.existsByIce(companyDTO.getIce())) {
            throw new DuplicateResourceException("Company with ICE " + companyDTO.getIce() + " already exists");
        }

        companyMapper.updateEntityFromDTO(companyDTO, company);
        Company updatedCompany = companyRepository.save(company);
        log.info("Company updated successfully with id: {}", id);
        return companyMapper.toDTO(updatedCompany);
    }

    @Transactional
    public void deleteCompany(Long id) {
        log.info("Deleting company with id: {}", id);

        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }

        companyRepository.deleteById(id);
        log.info("Company deleted successfully with id: {}", id);
    }
}
