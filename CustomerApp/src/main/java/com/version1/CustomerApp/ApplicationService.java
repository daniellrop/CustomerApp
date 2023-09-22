package com.version1.CustomerApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private CustomerRepository customerRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private CityRepository cityRepository;
//    @Autowired
//    private CountryRepository countryRepository;
    
    
    
//    public String sayHello() {
//    	return "HELLO WORLD!!!";
//    }
    
    public ApplicationService() {
    	
    }

    public ApplicationService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
    }

	public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
    }

    public String setNewCustomer(Customer customer) {
    	String result = "Customer Already exisits";
    	Optional<Customer> isExist = customerRepository.findById(customer.getId());
    	if(isExist.isPresent()) {
    		return result;
    	}
    	else {
            customerRepository.save(customer);
            return "Customer " + customer.getId() + " added!!!!";
    	}
    }

    public String updateCustomer(Customer customer) {
    	String result = "Customer not found";
    	Optional<Customer> isExist = customerRepository.findById(customer.getId());
    	if(isExist.isPresent()) {
            customerRepository.save(customer);
            return "Customer " + customer.getCustomerId() + " details updated!!!";
    	}
    	else {
    		return result;
    	}
    }

    public String deleteById(Integer customerId) {
    	Optional<Customer> result = customerRepository.findById(customerId);
    	if(result.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Customer deleted successfully!";
    	}
    	else {
    		return "Customer id " + customerId + " not found";
    	}
        	
    }
}
