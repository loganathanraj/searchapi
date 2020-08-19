package com.altimetric.searchAPI.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="locationdetail")
public class LocationDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "locationid")
	private Integer locationId;
	
	@Column
	private String source;
	
	@Column
	private String destination;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
