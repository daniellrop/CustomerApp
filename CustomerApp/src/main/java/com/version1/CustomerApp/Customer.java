package com.version1.CustomerApp;

import java.sql.Date;

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
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getPhonenumber1() {
		return phoneNumber1;
	}

	public void setPhonenumber1(String phonenumber1) {
		this.phoneNumber1 = phonenumber1;
	}

	public String getPhonenumber2() {
		return phoneNumber2;
	}

	public void setPhonenumber2(String phonenumber2) {
		this.phoneNumber2 = phonenumber2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
    
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerId=" + customerId + ", firstname=" + firstName + ", lastname="
				+ lastName + ", phonenumber1=" + phoneNumber1 + ", phonenumber2=" + phoneNumber2 + ", email=" + email
				+ ", company=" + company + "]";
	}
    
}