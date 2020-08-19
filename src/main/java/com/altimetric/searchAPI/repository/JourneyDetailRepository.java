package com.altimetric.searchAPI.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altimetric.searchAPI.entities.JourneyDetail;


@Repository
public interface JourneyDetailRepository extends  CrudRepository<JourneyDetail,Long> {
	
	public List<JourneyDetail> findByLocationIdAndTravelDateOrderByPriceAsc(Integer locationId, Date date);
	
	
}
