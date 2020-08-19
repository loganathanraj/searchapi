package com.altimetric.searchAPI.restController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altimetric.searchAPI.entities.FlightResponse;
import com.altimetric.searchAPI.entities.JourneyDetails;
import com.altimetric.searchAPI.entities.SearchRequest;
import com.altimetric.searchAPI.service.SearchFlightService;

@RestController
public class SearchController {
	
	@Autowired
	private SearchFlightService flightService;
	
	@PostMapping(path="/flight/search")
	public FlightResponse getFlightDetails(@Valid @RequestBody SearchRequest searchRequest) {
		
		List<JourneyDetails>  jouneyDetails = flightService.getJourneyInformation(searchRequest);
		
		FlightResponse response = new FlightResponse();
		response.setJourneyDetails(jouneyDetails);
		response.setNoOfFlights(jouneyDetails.size());
		return response;
	}
	

}
