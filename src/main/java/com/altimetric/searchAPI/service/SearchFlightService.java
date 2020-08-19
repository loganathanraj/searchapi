package com.altimetric.searchAPI.service;

import java.util.List;

import com.altimetric.searchAPI.entities.JourneyDetails;
import com.altimetric.searchAPI.entities.SearchRequest;

@FunctionalInterface
public interface SearchFlightService {

	public List<JourneyDetails> getJourneyInformation(SearchRequest searchRequest);
}
