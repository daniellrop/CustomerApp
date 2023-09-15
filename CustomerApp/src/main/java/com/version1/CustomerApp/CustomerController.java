package com.version1.CustomerApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomerController {

	@Autowired
	private ApplicationService appService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return appService.sayHello();
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		
		return appService.getAllCustomers();
	}
	
	@GetMapping("/customers/{id}")
	public Customer getUserById(@PathVariable (name = "id") Integer id) {
	
		return appService.getCustomerById(id);
	}
	
	@PostMapping ("/customers")
	public String setNewCustomer(@RequestBody Customer customer) {
		return appService.setNewCustomer(customer);
	}
	
	@PutMapping ("/customers")
	public String updateCustomer(@RequestBody Customer customer) {
		return appService.updateCustomer(customer);
	}
	
	@DeleteMapping ("/customers")
	public String deleteCustomer(@PathVariable (name ="id") Integer customerId) {
		return appService.deleteById(customerId);
	}
}
