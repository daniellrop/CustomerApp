package com.version1.CustomerAppTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.beans.Customizer;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.version1.CustomerApp.ApplicationService;
import com.version1.CustomerApp.City;
import com.version1.CustomerApp.Company;
import com.version1.CustomerApp.CompanyRepository;
import com.version1.CustomerApp.Country;
import com.version1.CustomerApp.Customer;
import com.version1.CustomerApp.CustomerController;
import com.version1.CustomerApp.CustomerRepository;
import com.version1.CustomerApp.User;


class CustomerNameTest {
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SecurityFilterChain filterChain;
	
	private Customer customer;
	private CustomerController customerController;
    private ApplicationService appService;
    private Country country;
    private Company company;
    private CompanyRepository companyRepository;
    private City city1;
    private User user;
    

	@BeforeEach
	public void setNewCustomer() {
		customer = new Customer();
		appService = mock(ApplicationService.class);
        customerController = new CustomerController(appService);
        country = new Country();
        
        companyRepository = mock(CompanyRepository.class);

        // Mock the behavior to return the sample company when getById is called
        when(companyRepository.getById(401)).thenReturn(new Company(401, "Peck PLC"));

        // Create a new Company instance
        company = new Company(401, "Peck PLC");
        city1 = new City(16672, "Geoffreymouth", new Country(139, "Paraguay"));
        
        user = new User();
        user.setId(1);
        user.setUsername("user3");
        user.setPassword("password");
        user.setActive(true);
        user.setRole("ADMIN");
        
   

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
	
	@Test
    public void testGetId() {
        assertEquals(401, company.getCompanyId());
    }

    @Test
    public void testGetName() {
        assertEquals("Peck PLC", company.getCompanyName());
    }
	
	@Test
	public void firstNameLengthtest() {
		customer.setFirstname("Sneha");
		assertTrue(customer.getFirstname().length() >= 3 && customer.getFirstname().length() <= 25);
	}
	
	@Test
    public void firstNameWithNumericalCharactersTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setFirstname("Sneha123");
        });
    }

    @Test
    public void firstNameWithSpecialCharactersTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setFirstname("Sneha@K");
        });
    }

    @Test
    public void nullFirstNameTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setFirstname(null);
        });
    }

	@Test
	public void lastNameLengthtest() {
		customer.setLastname("Sneha");
		assertTrue(customer.getLastname().length() >= 3 && customer.getLastname().length() <= 15);
	}
	
	@Test
    public void lastNameWithNumericalCharactersTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setLastname("K123");
        });
    }

    @Test
    public void lastNameWithSpecialCharactersTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setLastname("K@Sneha");
        });
    }

    @Test
    public void nullLastNameTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setLastname(null);
        });
    }
    
    @Test
    public void emailTest() {
        customer.setEmail("sneha.k@example.com");
        assertEquals("sneha.k@example.com", customer.getEmail());
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("invalid-email");
        });
    }

    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail(null);
        });
    }
    
    @Test
    public void testValidSubscriptionDate() {
        LocalDate validDate = LocalDate.of(2023, 9, 15);
    	//Date validDate = "12/10/2009";
        customer.setSubscriptionDate(Date.valueOf(validDate));
        assertEquals(Date.valueOf(validDate), customer.getSubscriptionDate());
    }

    @Test
    public void testInvalidSubscriptionDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setSubscriptionDate(Date.valueOf("15-09-2030")); 
        });
    }

    @Test
    public void testNullSubscriptionDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setSubscriptionDate(null);
        });
    }
    
    @Test
    public void testValidPhoneNumber1() {
        Customer customer = new Customer();
        customer.setPhonenumber1("+123-456-7890");
        assertEquals("+123-456-7890", customer.getPhonenumber1());
    }

    @Test
    public void testValidPhoneNumber2() {
        Customer customer = new Customer();
        customer.setPhonenumber2("1234");
        assertEquals("1234", customer.getPhonenumber2());
    }

    @Test
    public void testInvalidPhoneNumber1() {
        Customer customer = new Customer();
        assertThrows(IllegalArgumentException.class, () -> customer.setPhonenumber1("invalid"));
    }

    @Test
    public void testInvalidPhoneNumber2() {
        Customer customer = new Customer();
        assertThrows(IllegalArgumentException.class, () -> customer.setPhonenumber2("invalid"));
    }

    @Test
    public void testNullPhoneNumber1() {
        Customer customer = new Customer();
        customer.setPhonenumber1(null);
        assertNull(customer.getPhonenumber1());
    }

    @Test
    public void testNullPhoneNumber2() {
        Customer customer = new Customer();
        customer.setPhonenumber2(null);
        assertNull(customer.getPhonenumber2());
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
    
    //NEW


    @Test
    public void testInvalidEmailMissingAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("invalid-email.com");
        });
    }

    @Test
    public void testInvalidEmailMissingDomain() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("invalid-email@");
        });
    }

//    @Test
//    public void testInvalidEmailSpecialCharacters() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            customer.setEmail("invalid@e#mail.com");
//        });
//    }

    @Test
    public void testNullEmail1() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail(null);
        });
    }

//    @Test
//    public void testInvalidDate() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            customer.setSubscriptionDate(Date.valueOf("2023-09-15")); // Invalid format
//        });
//    }

    @Test
    public void testNullSubscriptionDate1() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.setSubscriptionDate(null);
        });
    }

    @Test
    public void testValidPhoneNumber11() {
        customer.setPhonenumber1("+123-456-7890");
        assertEquals("+123-456-7890", customer.getPhonenumber1());
    }

    @Test
    public void testValidPhoneNumber21() {
        customer.setPhonenumber2("1234");
        assertEquals("1234", customer.getPhonenumber2());
    }

    @Test
    public void testInvalidPhoneNumber11() {
        assertThrows(IllegalArgumentException.class, () -> customer.setPhonenumber1("invalid"));
    }

    @Test
    public void testInvalidPhoneNumber21() {
        assertThrows(IllegalArgumentException.class, () -> customer.setPhonenumber2("invalid"));
    }

    @Test
    public void testNullPhoneNumber11() {
        customer.setPhonenumber1(null);
        assertNull(customer.getPhonenumber1());
    }

    @Test
    public void testNullPhoneNumber21() {
        customer.setPhonenumber2(null);
        assertNull(customer.getPhonenumber2());
    }

    @Test
    public void testValidCityAssociation() {
        customer.setCity(city1);
        assertEquals(city1, customer.getCity());
    }
//
//    @Test
//    public void testNullCityAssociation() {
//        assertThrows(IllegalArgumentException.class, () -> customer.setCity(null));
//    }

    @Test
    public void testValidCompanyAssociation() {
        customer.setCompany(company);
        assertEquals(company, customer.getCompany());
    }

//    @Test
//    public void testNullCompanyAssociation() {
//        assertThrows(IllegalArgumentException.class, () -> customer.setCompany(null));
//    }
//    
//    @Test
//    public void testPasswordEncoderBean1() {
//        // Ensure that the BCryptPasswordEncoder bean is created and not null
//        assert passwordEncoder != null;
//    }

//    @Test
//    public void testFilterChainBean1() {
//        // Ensure that the SecurityFilterChain bean is created and not null
//        assert filterChain != null;
//    }

//    @Test
//    public void testPasswordEncoderBean() {
//        // Ensure that the BCryptPasswordEncoder bean is created and not null
//        assert passwordEncoder != null;
//    }

//    @Test
//    public void testFilterChainBean() {
//        // Ensure that the SecurityFilterChain bean is created and not null
//        assert filterChain != null;
//    }

    @Configuration
    static class TestConfig {

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(4);
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return mock(UserDetailsService.class);
        }

        @Bean
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/customers").hasAuthority("ADMIN")
                    .requestMatchers("/customers/**").hasAuthority("ADMIN")
            )
                    .httpBasic();
            return http.build();
        }
    }
    
    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Ensure that the user has the expected authority
        assertTrue(authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN")));
    }

    @Test
    public void testGetId1() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testIsActive() {
        assertTrue(user.isActive());
    }

    @Test
    public void testGetRole() {
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("newusername");
        assertEquals("newusername", user.getUsername());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(user.isEnabled());
    }
}
