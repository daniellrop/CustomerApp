package com.version1.CustomerAppTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.version1.CustomerApp.City;
import com.version1.CustomerApp.Country;

public class CityTest {

    private City city1;
    private City city2;

    @BeforeEach
    public void setUp() {
        city1 = new City(16672, "Geoffreymouth", new Country(139, "Paraguay"));
    }

    @Test
    public void testCityConstructor() {
        assertEquals(16672, city1.getCityId());
        assertEquals("Geoffreymouth", city1.getCityName());
    }

    @Test
    public void testCityHashCode() {
        City anotherCity1 = new City(16672, "Geoffreymouth", new Country(139, "Paraguay"));
        assertEquals(city1.hashCode(), anotherCity1.hashCode());
    }

    @Test
    public void testCityNotNull() {
        assertNotNull(city1);
    }

}
