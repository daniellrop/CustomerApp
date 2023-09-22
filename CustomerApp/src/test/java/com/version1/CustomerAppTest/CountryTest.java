package com.version1.CustomerAppTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.version1.CustomerApp.Country;

class CountryTest {

	private Country country;

    @BeforeEach
    public void setUp() {
        country = new Country();
    }

    @Test
    public void testValidCountryName() {
        country.setCountryName("United States");
        assertEquals("United States", country.getCountryName());
    }

    @Test
    public void testValidCountryId() {
        country.setCountryId(1); 
        assertEquals(1, country.getCountryId()); 
    }

    @Test
    public void testValidCountryNameWithSpaces() {
        country.setCountryName("United Kingdom");
        assertEquals("United Kingdom", country.getCountryName());
    }

    @Test
    public void testValidCountryNameWithSpecialCharacters() {
        country.setCountryName("New Zealand");
        assertEquals("New Zealand", country.getCountryName());
    }

    @Test
    public void testValidCountryNameWithNumbers() {
        country.setCountryName("South Korea");
        assertEquals("South Korea", country.getCountryName());
    }

}
