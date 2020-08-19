package com.altimetric.searchAPI.entities;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SearchRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String	sourceCity;
	
	@NotNull
	private String destinationCity;
	
	@NotNull
	private String travelDate;
	
	
	private String returnDate;
	
	private String sortValue;
	
	private String sortOrder;

	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
}
