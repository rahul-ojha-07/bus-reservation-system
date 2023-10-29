package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.TripScheduleDto;
import com.example.brs.entity.bus.Trip;
import com.example.brs.entity.bus.TripSchedule;

public class TripScheduleMapper {
    public static TripScheduleDto toTripScheduleDto(TripSchedule tripSchedule) {
        Trip tripDetails = tripSchedule.getTripDetails();
        return TripScheduleDto.builder()
                .withId(tripSchedule.getId())
                .withTripId(tripDetails.getId())
                .withBusCode(tripDetails.getBus().getCode())
                .withAvailableSeats(tripSchedule.getAvailableSeats())
                .withFare(tripDetails.getFare())
                .withJourneyTime(tripDetails.getJourneyTime())
                .withSourceStop(tripDetails.getSourceStop().getName())
                .withDestinationStop(tripDetails.getDestStop().getName())
                .build();
    }
}
