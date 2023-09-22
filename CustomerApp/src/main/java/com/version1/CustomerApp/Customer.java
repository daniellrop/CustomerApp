package com.version1.CustomerApp;

import java.sql.Date;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(name = "customerID")
    private String customerId;
	@Column
    private String firstName;
	@Column
    private String lastName;
	@Column
    private String phoneNumber1;
	@Column
    private String phoneNumber2;
	@Column
    private String email;
	@Column
    private Date subscriptionDate;
    
    public Customer() {
    	
    }

    public Customer(Integer id, String customerId, String firstname, String lastname, String phonenumber1,
			String phonenumber2, String email, Date subscriptionDate, Company company, City city) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.firstName = firstname;
		this.lastName = lastname;
		this.phoneNumber1 = phonenumber1;
		this.phoneNumber2 = phonenumber2;
		this.email = email;
		this.subscriptionDate = subscriptionDate;
		this.company = company;
		this.city = city;
		
	}

	@ManyToOne
    @JoinColumn(name = "companyid")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private City city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		try {
            // Check if the input is not null and does not contain numerical digits or special characters
            if (firstname != null && firstname.matches("^[a-zA-Z]+$")) {
                this.firstName = firstname;
            } else {
                throw new IllegalArgumentException("First name is not valid. It should contain only alphabetic characters.");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("First name cannot be null.");
        }
    }

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		try {
            // Check if the input is not null and does not contain numerical digits or special characters
            if (lastname != null && lastname.matches("^[a-zA-Z]+$")) {
                this.lastName = lastname;
            } else {
                throw new IllegalArgumentException("Last name is not valid. It should contain only alphabetic characters.");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Last name cannot be null.");
        }
	}

	public String getPhonenumber1() {
		return phoneNumber1;
	}

	public void setPhonenumber1(String phonenumber1) {
		try {
	        if (phonenumber1 == null || phonenumber1.matches("^[+\\-\\(]?[0-9]?[0-9]?[-]?[0-9]{3}[0-9|-|\\)|.]?[-]?[0-9]{3}[-|.]?[0-9]{2}[0-9]?[-|.]?[0-9]?[x]?[0-9]?[0-9]?[0-9]?[0-9]{0,3}$|^[-]?[0-9]{4}$")) {
	            this.phoneNumber1 = phonenumber1;
	        } else {
	            throw new IllegalArgumentException("Invalid phone number format for phoneNumber1.");
	        }
	    } catch (NullPointerException e) {
	        throw new IllegalArgumentException("Phone number1 cannot be null.");
	    }
	}

	public String getPhonenumber2() {
		return phoneNumber2;
	}

	public void setPhonenumber2(String phonenumber2) {
		try {
	        if (phonenumber2 == null || phonenumber2.matches("^[+\\-\\(]?[0-9]?[0-9]?[-]?[0-9]{3}[0-9|-|\\)|.]?[-]?[0-9]{3}[-|.]?[0-9]{2}[0-9]?[-|.]?[0-9]?[x]?[0-9]?[0-9]?[0-9]?[0-9]{0,3}$|^[-]?[0-9]{4}$")) {
	            this.phoneNumber2 = phonenumber2;
	        } else {
	            throw new IllegalArgumentException("Invalid phone number format for phoneNumber2.");
	        }
	    } catch (NullPointerException e) {
	        throw new IllegalArgumentException("Phone number2 cannot be null.");
	    }
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		try {
            if (email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                this.email = email;
            } else {
                throw new IllegalArgumentException("Invalid email address.");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Email cannot be null.");
        }
	}

	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		try {
            this.company = company;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Company cannot be null.");
        }
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		try {
            if (subscriptionDate != null) {
                this.subscriptionDate = subscriptionDate;
            } else {
                throw new IllegalArgumentException("Subscription date cannot be null.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use the 'dd-MM-yyyy' format.");
        }
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		try {
            this.city = city;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("City cannot be null.");
        }
	}
    
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerId=" + customerId + ", firstname=" + firstName + ", lastname="
				+ lastName + ", phonenumber1=" + phoneNumber1 + ", phonenumber2=" + phoneNumber2 + ", email=" + email
				+ ", company=" + company + ", subscriptionDate=" + subscriptionDate + "]";
	}
    
}