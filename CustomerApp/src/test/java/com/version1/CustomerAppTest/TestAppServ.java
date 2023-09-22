package com.version1.CustomerAppTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.version1.CustomerApp.ApplicationService;
import com.version1.CustomerApp.Customer;
import com.version1.CustomerApp.CustomerRepository;

public class TestAppServ {

    private ApplicationService applicationService;
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        applicationService = new ApplicationService(customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = applicationService.getAllCustomers();

        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Customer result = applicationService.getCustomerById(customerId);

        assertEquals(customer, result);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            applicationService.getCustomerById(customerId);
        });
    }

    @Test
    public void testSetNewCustomer() {
        Customer customer = new Customer();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        String result = applicationService.setNewCustomer(customer);

        assertEquals("Customer " + customer.getId() + " added!!!!", result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testSetNewCustomerAlreadyExists() {
        Customer customer = new Customer();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        String result = applicationService.setNewCustomer(customer);

        assertEquals("Customer Already exisits", result);
        verify(customerRepository, never()).save(customer);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        String result = applicationService.updateCustomer(customer);

        assertEquals("Customer " + customer.getCustomerId() + " details updated!!!", result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomerNotFound() {
        Customer customer = new Customer();
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        String result = applicationService.updateCustomer(customer);

        assertEquals("Customer not found", result);
        verify(customerRepository, never()).save(customer);
    }

    @Test
    public void testDeleteById() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));

        String result = applicationService.deleteById(customerId);

        assertEquals("Customer deleted successfully!", result);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void testDeleteByIdNotFound() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        String result = applicationService.deleteById(customerId);

        assertEquals("Customer id " + customerId + " not found", result);
        verify(customerRepository, never()).deleteById(customerId);
    }
}
