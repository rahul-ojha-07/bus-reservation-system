package com.example.brs.service;

import com.example.brs.dto.entity.bus.*;
import com.example.brs.dto.entity.user.UserDto;

import java.util.List;
import java.util.Set;

public interface BusReservationService {
    Set<StopDto> getAllStop();
    StopDto getStopByCode(String stopCode);

    AgencyDto getAgency(UserDto userDto);
    AgencyDto addAgency(AgencyDto agencyDto);
    AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto);

    TripDto getTripById(Long tripId);
    List<TripDto> addTrip(TripDto tripDto);
    List<TripDto> getAgencyTrips(String agencyCode);

    List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode);

    List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode, String tripDate);

    TripScheduleDto getTripSchedule(TripDto tripDto, String tripDate, boolean createScheduleForTrip);
    TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger);

}
