package com.altimetric.searchAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetric.searchAPI.entities.FlightDetail;

@Repository
public interface FlightDetailRespository extends CrudRepository<FlightDetail, Long>{

	public FlightDetail findByFlightNumber(Integer flightNumber);
	
	
}
