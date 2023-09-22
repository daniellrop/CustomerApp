package com.version1.CustomerAppTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

import com.version1.CustomerApp.ApplicationService;
import com.version1.CustomerApp.Customer;
import com.version1.CustomerApp.CustomerController;

public class CustomerControllerTest {

    private CustomerController customerController;
    private ApplicationService appService;

    @BeforeEach
    public void setUp() {
        appService = mock(ApplicationService.class);
        customerController = new CustomerController(appService);
        
    }



    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        // Add some sample customers to the list

        when(appService.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerController.getAllCustomers();
        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer();
        // Set up customer and mock service to return it

        when(appService.getCustomerById(customerId)).thenReturn(customer);

        Customer result = customerController.getUserById(customerId);
        assertEquals(customer, result);
    }

    @Test
    public void testSetNewCustomer() {
    	Customer customer = new Customer();
        // Set up customer and mock service to return a success message

        when(appService.setNewCustomer(customer)).thenReturn("Customer added successfully");

        String result = customerController.setNewCustomer(customer);
        assertEquals("Customer added successfully", result);
    }

    @Test
    public void testUpdateCustomer() {
    	Customer customer = new Customer();
        // Set up customer and mock service to return a success message

        when(appService.updateCustomer(customer)).thenReturn("Customer updated successfully");

        String result = customerController.updateCustomer(customer);
        assertEquals("Customer updated successfully", result);
    }

    @Test
    public void testDeleteCustomer() {
    	int customerId = 1;
        // Mock service to return a success message

        when(appService.deleteById(customerId)).thenReturn("Customer deleted successfully");

        String result = customerController.deleteCustomer(customerId);
        assertEquals("Customer deleted successfully", result);
    
    }
}

