package com.version1.CustomerApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomerController {

	private final ApplicationService appService;

    @Autowired
    public CustomerController(ApplicationService appService) {
        this.appService = appService;
    }
    
    @CrossOrigin(origins = "http://127.0.0.1:5173")
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		
		return appService.getAllCustomers();
	}
	
    @CrossOrigin(origins = "http://127.0.0.1:5173")
	@GetMapping("/customers/{id}")
	public Customer getUserById(@PathVariable (name = "id") Integer id) {
	
		return appService.getCustomerById(id);
	}
	
    @CrossOrigin(origins = "http://127.0.0.1:5173")
	@PostMapping ("/customers")
	public String setNewCustomer(@RequestBody Customer customer) {
		return appService.setNewCustomer(customer);
	}
	
    @CrossOrigin(origins = "http://127.0.0.1:5173")
	@PutMapping ("/customers")
	public String updateCustomer(@RequestBody Customer customer) {
		return appService.updateCustomer(customer);
	}
	
    @CrossOrigin(origins = "http://127.0.0.1:5173")
	@DeleteMapping ("/customers/{id}")
	public String deleteCustomer(@PathVariable (name ="id") Integer customerId) {
		return appService.deleteById(customerId);
	}
}
