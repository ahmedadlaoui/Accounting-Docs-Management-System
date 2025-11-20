package com.example.demo.config;

import com.example.demo.model.entity.Company;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // Create test companies
            Company company1 = new Company();
            company1.setCompanyName("Al Baraka SARL");
            company1.setIce("001234567890123");
            company1.setAddress("12 Avenue Hassan II, Casablanca");
            company1.setPhone("0612345678");
            company1.setContactEmail("contact@albaraka.ma");
            companyRepository.save(company1);

            Company company2 = new Company();
            company2.setCompanyName("Riad Trading");
            company2.setIce("001234567890456");
            company2.setAddress("25 Rue Mohammed V, Rabat");
            company2.setPhone("0698765432");
            company2.setContactEmail("info@riadtrading.ma");
            companyRepository.save(company2);

            log.info("Test companies created: Al Baraka SARL and Riad Trading");

            // Create a test accountant user
            User comptable = new User();
            comptable.setEmail("comptable@alamane.ma");
            comptable.setPassword(passwordEncoder.encode("password123"));
            comptable.setFullName("Ahmed Benani");
            comptable.setRole(Role.COMPTABLE);
            comptable.setIsActive(true);
            userRepository.save(comptable);

            log.info("Test accountant user created: comptable@alamane.ma / password123");

            // Create a test company user
            User societe = new User();
            societe.setEmail("societe@company.ma");
            societe.setPassword(passwordEncoder.encode("password123"));
            societe.setFullName("Fatima Zahra");
            societe.setRole(Role.SOCIETE);
            societe.setCompany(company1);
            societe.setIsActive(true);
            userRepository.save(societe);

            log.info("Test company user created: societe@company.ma / password123");
        }
    }
}
