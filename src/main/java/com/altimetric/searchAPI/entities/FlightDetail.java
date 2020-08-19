package com.altimetric.searchAPI.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name= "flightdetail")
public class FlightDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "flightnumber")
	private Integer flightNumber;

	@Column(name = "airplanename")
	private String airplaneName;

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirplaneName() {
		return airplaneName;
	}

	public void setAirplaneName(String airplaneName) {
		this.airplaneName = airplaneName;
	}
	
	
	
}
