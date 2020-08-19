package com.altimetric.searchAPI;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetric.searchAPI.entities.FlightDetail;
import com.altimetric.searchAPI.entities.JourneyDetail;
import com.altimetric.searchAPI.entities.JourneyDetails;
import com.altimetric.searchAPI.entities.LocationDetail;
import com.altimetric.searchAPI.entities.SearchRequest;
import com.altimetric.searchAPI.repository.FlightDetailRespository;
import com.altimetric.searchAPI.repository.JourneyDetailRepository;
import com.altimetric.searchAPI.repository.LocationDetailRepository;
import com.altimetric.searchAPI.service.Impl.SearchFlightServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SearchApiApplicationTests {
	
	@InjectMocks
	private SearchFlightServiceImpl searchFlightService;
	
	@Mock
	private LocationDetailRepository locationRepository;
	
	@Mock
	private JourneyDetailRepository journeyRepository;

	@Mock
	private FlightDetailRespository flightRepository;
	
	@Test
	public void testFlightNumberDuplicateRemoval()
	{
		List<JourneyDetails> journeyDetails=new ArrayList<>();
		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-22");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(1);
		journey2.setPrice(1000.00);
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		
		Mockito.when(flightRepository.findByFlightNumber(Mockito.any())).thenReturn(new FlightDetail());
		journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("size of the data is not matched", 1 == journeyDetails.size());
	}
	
	@Test
	public void testPriceDefaultOrder()
	{
		List<JourneyDetails> journeyDetails=new ArrayList<>();
		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-23");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(2);
		journey2.setPrice(1000.00);
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		
		Mockito.when(flightRepository.findByFlightNumber(Mockito.any())).thenReturn(new FlightDetail());
		journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("value matched", "2".equalsIgnoreCase(journeyDetails.get(0).getFlightNumber()));
	}
	
	@Test
	public void testPriceDesctOrder()
	{
		List<JourneyDetails> journeyDetails=new ArrayList<>();
		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-24");
		search.setSortValue("price");
		search.setSortOrder("desc");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(2);
		journey2.setPrice(1000.00);
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		
		Mockito.when(flightRepository.findByFlightNumber(Mockito.any())).thenReturn(new FlightDetail());
		journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("value matched", "1".equalsIgnoreCase(journeyDetails.get(0).getFlightNumber()));
	}
	
	@Test
	public void testAirLineOrder()
	{
		List<JourneyDetails> journeyDetails=new ArrayList<>();
		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-25");
		search.setSortValue("airlines name");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(2);
		journey2.setPrice(1000.00);
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		FlightDetail f1 = new FlightDetail();
		f1.setAirplaneName("Jet Airways");
		Mockito.when(flightRepository.findByFlightNumber(1)).thenReturn(f1);
		
		FlightDetail f2 = new FlightDetail();
		f2.setAirplaneName("Indigo");
		Mockito.when(flightRepository.findByFlightNumber(2)).thenReturn(f2);
		journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("Ordered mismatched on airline name", "Indigo".equalsIgnoreCase(journeyDetails.get(0).getAirLineName()));
	}
	
	@Test
	public void testDepartureOrder()
	{
		List<JourneyDetails> journeyDetails=new ArrayList<>();
		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-26");
		search.setSortValue("departure time");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journey.setDepartureTime(new Timestamp(Long.valueOf(1597822200000l)));
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(2);
		journey2.setPrice(1000.00);
		journey2.setDepartureTime(new Timestamp(Long.valueOf(1597811400000l)));
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		FlightDetail f1 = new FlightDetail();
		f1.setAirplaneName("Jet Airways");
		Mockito.when(flightRepository.findByFlightNumber(1)).thenReturn(f1);
		
		FlightDetail f2 = new FlightDetail();
		f2.setAirplaneName("Indigo");
		Mockito.when(flightRepository.findByFlightNumber(2)).thenReturn(f2);
		journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("department Order is mismatched", "Indigo".equalsIgnoreCase(journeyDetails.get(0).getAirLineName()));
	}
	
	
	@Test
	public void testInMemoryConfig() {

		SearchRequest search = new SearchRequest();
		search.setSourceCity("Chennai");
		search.setDestinationCity("Bangalore");
		search.setTravelDate("2020-08-21");
		LocationDetail location = new LocationDetail();
		location.setLocationId(1);
		Mockito.when(locationRepository.findBySourceAndDestination(Mockito.anyString(), Mockito.anyString())).thenReturn(location);
		List<JourneyDetail> journeyList = new ArrayList<>();
		JourneyDetail journey = new JourneyDetail();
		journey.setFlightNumber(1);
		journey.setPrice(1500.00);
		journeyList.add(journey);
		
		JourneyDetail journey2 = new JourneyDetail();
		journey2.setFlightNumber(1);
		journey2.setPrice(1000.00);
		journeyList.add(journey2);
		
		Mockito.when(journeyRepository.findByLocationIdAndTravelDateOrderByPriceAsc(Mockito.any(), Mockito.any())).thenReturn(journeyList);
		
		Mockito.when(flightRepository.findByFlightNumber(Mockito.any())).thenReturn(new FlightDetail());
		List<JourneyDetails> journeyDetails = searchFlightService.getJourneyInformation(search);
		Assert.assertTrue("size of the data is not matched", 1 == journeyDetails.size());
		StringBuilder build = new StringBuilder();
		build.append(search.getSourceCity()).append(":").append(search.getDestinationCity())
		.append(":").append(search.getTravelDate());
		Assert.assertTrue("Issue with inmemory cache","1".equalsIgnoreCase(searchFlightService.inMemoryCache.get(build.toString()).get(0).getFlightNumber()));
	}
	
 }
