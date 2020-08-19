package com.altimetric.searchAPI.entities;

import java.io.Serializable;
import java.sql.Timestamp;


public class JourneyDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String flightNumber;
	
	private String airLineName;
	
	private Timestamp departureTime;
	
	private Timestamp arrivalTime;
	
	private String duration;
	
	private String noOfStops;
	
	private Double price;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	

	public String getAirLineName() {
		return airLineName;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}

	public Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNoOfStops() {
		return noOfStops;
	}

	public void setNoOfStops(String noOfStops) {
		this.noOfStops = noOfStops;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
