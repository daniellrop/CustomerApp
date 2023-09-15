package com.version1.CustomerApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class City {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long cityId;
	
	@Column
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name = "countryID")
	private Country country;
	
	public City() {
		
	}
	
	
	
	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", country=" + country + "]";
	}

	

	public City(long cityId, String cityName, Country country) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.country = country;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	//getters and setters below

	
	
	
	
	
	

}
