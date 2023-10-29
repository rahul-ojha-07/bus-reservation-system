package com.example.brs.service;

import com.example.brs.dto.entity.bus.TripDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {
    TripDto getTripById(Long tripId);
    List<TripDto> addTrip(TripDto tripDto);
    List<TripDto> getAgencyTrips(String agencyCode);

    List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode);
}
