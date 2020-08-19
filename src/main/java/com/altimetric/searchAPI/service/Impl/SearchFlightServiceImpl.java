package com.altimetric.searchAPI.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.altimetric.searchAPI.entities.FlightDetail;
import com.altimetric.searchAPI.entities.JourneyDetail;
import com.altimetric.searchAPI.entities.JourneyDetails;
import com.altimetric.searchAPI.entities.LocationDetail;
import com.altimetric.searchAPI.entities.SearchRequest;
import com.altimetric.searchAPI.repository.FlightDetailRespository;
import com.altimetric.searchAPI.repository.JourneyDetailRepository;
import com.altimetric.searchAPI.repository.LocationDetailRepository;
import com.altimetric.searchAPI.service.SearchFlightService;

@Service
public class SearchFlightServiceImpl implements SearchFlightService {

	public static Map<String, List<JourneyDetails>> inMemoryCache = new HashMap<>();
	@Autowired
	private JourneyDetailRepository journeyRepository;

	@Autowired
	private LocationDetailRepository locationRepository;

	@Autowired
	private FlightDetailRespository flightRepository;
	

	@Override
	public List<JourneyDetails> getJourneyInformation(SearchRequest searchRequest) {

		StringBuilder cacheValue = new StringBuilder();
		cacheValue.append(searchRequest.getSourceCity()).append(":").append(searchRequest.getDestinationCity())
				.append(":").append(searchRequest.getTravelDate());
		Set<Integer> flightNumber = new HashSet<>();
		LocationDetail location = locationRepository.findBySourceAndDestination(searchRequest.getSourceCity(),
				searchRequest.getDestinationCity());
		List<JourneyDetails> journeyList = new ArrayList<>();

		if (inMemoryCache.get(cacheValue.toString()) == null) {

			try {
				List<JourneyDetail> details = journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(
						location.getLocationId(),
						new SimpleDateFormat("yyyy-MM-dd").parse(searchRequest.getTravelDate()));
				populateJourneyDetails(journeyList, details,flightNumber);
				inMemoryCache.put(cacheValue.toString(), journeyList);
				} catch (ParseException e) {
				return null;
			}
		} else {
			journeyList .addAll(inMemoryCache.get(cacheValue.toString()));
		}
		if (!StringUtils.isEmpty(searchRequest.getReturnDate())) {
			StringBuilder returnCache = new StringBuilder();
			returnCache.append(searchRequest.getDestinationCity()).append(":").append(searchRequest.getSourceCity())
					.append(":").append(searchRequest.getTravelDate());
			returnCache.append(":").append(searchRequest.getReturnDate());
			if (inMemoryCache.get(returnCache.toString()) == null) {

				location = locationRepository.findBySourceAndDestination(searchRequest.getDestinationCity(),
						searchRequest.getSourceCity());
				try {
					List<JourneyDetail> details = journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(
							location.getLocationId(),
							new SimpleDateFormat("yyyy-MM-dd").parse(searchRequest.getReturnDate()));
					populateJourneyDetails(journeyList, details, flightNumber);
					inMemoryCache.put(returnCache.toString(), journeyList);
				} catch (ParseException e) {
					return null;
				}
			} else {
				journeyList.addAll(inMemoryCache.get(returnCache.toString()));
			}
		}

		if (StringUtils.isEmpty(searchRequest.getSortValue())) {
			Collections.sort(journeyList, new Comparator<JourneyDetails>() {

				@Override
				public int compare(JourneyDetails o1, JourneyDetails o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}

			});

		} else if (!StringUtils.isEmpty(searchRequest.getSortValue())) {
			if (searchRequest.getSortValue().equalsIgnoreCase("airlines name")) {
				Collections.sort(journeyList, new Comparator<JourneyDetails>() {

					@Override
					public int compare(JourneyDetails o1, JourneyDetails o2) {
						return o1.getAirLineName().compareTo(o2.getAirLineName());
					}
				});

			} else if (searchRequest.getSortValue().equalsIgnoreCase("departure time")) {
				Collections.sort(journeyList, new Comparator<JourneyDetails>() {

					@Override
					public int compare(JourneyDetails o1, JourneyDetails o2) {
						return o1.getDepartureTime().compareTo(o2.getDepartureTime());
					}
				});

			}

			else if (searchRequest.getSortValue().equalsIgnoreCase("duration")) {
				Collections.sort(journeyList, new Comparator<JourneyDetails>() {
					@Override
					public int compare(JourneyDetails o1, JourneyDetails o2) {
						return o1.getDuration().compareTo(o2.getDuration());
					}
				});

			}

			else if (searchRequest.getSortValue().equalsIgnoreCase("price")
					&& searchRequest.getSortOrder().equalsIgnoreCase("Desc")) {
				Collections.sort(journeyList, Collections.reverseOrder(new Comparator<JourneyDetails>() {

					@Override
					public int compare(JourneyDetails o1, JourneyDetails o2) {
						return o1.getPrice().compareTo(o2.getPrice());
					}
				}));
			}

		}

		return journeyList;
	}

	private void populateJourneyDetails(List<JourneyDetails> journeyList, List<JourneyDetail> details,Set<Integer> flightNumber) {
		for (JourneyDetail detail : details) {
			if(flightNumber.contains(detail.getFlightNumber())) {
				continue;
			}
			JourneyDetails journeyDetail = new JourneyDetails();
			journeyDetail.setArrivalTime(detail.getArrivalTime());
			journeyDetail.setDepartureTime(detail.getDepartureTime());
			journeyDetail.setFlightNumber(String.valueOf(detail.getFlightNumber()));
			journeyDetail.setNoOfStops(String.valueOf(detail.getStop()));
			journeyDetail.setPrice(detail.getPrice());
			if(detail.getArrivalTime()!=null)
			journeyDetail.setDuration(
					String.valueOf(detail.getArrivalTime().getTime()- detail.getDepartureTime().getTime()));
			FlightDetail flightDetail = flightRepository.findByFlightNumber(detail.getFlightNumber());
			journeyDetail.setAirLineName(flightDetail.getAirplaneName());
			journeyList.add(journeyDetail);
			flightNumber.add(detail.getFlightNumber());		
		}
	}

}


