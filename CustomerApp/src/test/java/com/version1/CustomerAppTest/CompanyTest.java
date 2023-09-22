package com.version1.CustomerAppTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.version1.CustomerApp.Company;
import com.version1.CustomerApp.CompanyRepository;

public class CompanyTest {

    private Company company;
    private CompanyRepository companyRepository; // Replace with your actual repository or service

    @BeforeEach
    public void setUp() {
        // Create a mock for the repository or service
        companyRepository = mock(CompanyRepository.class);

        // Mock the behavior to return the sample company when getById is called
        when(companyRepository.getById(401)).thenReturn(new Company(401, "Peck PLC"));

        // Create a new Company instance
        company = new Company(401, "Peck PLC");
    }

    @Test
    public void testGetId() {
        // Test the getId() method
        assertEquals(401, company.getCompanyId());
    }

    @Test
    public void testGetName() {
        // Test the getName() method
        assertEquals("Peck PLC", company.getCompanyName());
    }
}

