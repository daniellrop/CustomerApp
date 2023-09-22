package com.version1.CustomerApp;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "cities")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cityId;
	@Column
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name = "countryID")
	private Country country;
	
	public City() {
		
	}
	
	public City(Integer cityId, String cityName, Country country) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.country = country;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
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
		try {
            this.country = country;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Country cannot be null.");
        }
	}
	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", country=" + country + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    City otherCity = (City) obj;
	    return cityId == otherCity.cityId &&
	           Objects.equals(cityName, otherCity.cityName);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(cityId, cityName);
	}
}
