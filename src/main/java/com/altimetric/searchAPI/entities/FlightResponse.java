package com.altimetric.searchAPI.entities;

import java.util.List;

public class FlightResponse {
	
	private List<JourneyDetails> journeyDetails;
	
	private Integer noOfFlights;

	public List<JourneyDetails> getJourneyDetails() {
		return journeyDetails;
	}

	public void setJourneyDetails(List<JourneyDetails> journeyDetails) {
		this.journeyDetails = journeyDetails;
	}

	public Integer getNoOfFlights() {
		return noOfFlights;
	}

	public void setNoOfFlights(Integer noOfFlights) {
		this.noOfFlights = noOfFlights;
	} 
	
	
	
}
