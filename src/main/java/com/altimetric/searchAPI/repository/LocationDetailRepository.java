package com.altimetric.searchAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetric.searchAPI.entities.LocationDetail;

@Repository
public interface LocationDetailRepository extends CrudRepository<LocationDetail, Long> {
	
	public LocationDetail findBySourceAndDestination(String source,String destination);
	
	
}
