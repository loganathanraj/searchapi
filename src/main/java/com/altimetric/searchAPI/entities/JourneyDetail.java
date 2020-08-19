package com.altimetric.searchAPI.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "journeydetail")
public class JourneyDetail implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "journeyid")
	private Integer journeyId;
	
	@Column(name="flightnumber")
	private Integer flightNumber;
	
	@Column(name="locationid")
	private Integer locationId;
	
	@Column(name= "traveldate")
	private Date travelDate;
	
	@Column(name = "arrivaltime")
	private Timestamp arrivalTime;
	
	@Column(name="departuretime")
	private Timestamp departureTime;
	
	@Column(name="stop")
	private Integer stop;
	
	@Column(name="price")
	private Double price;

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}

	

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
	

}
