package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.TripDto;
import com.example.brs.entity.bus.Trip;

public class TripMapper {
    public static TripDto toTripDto(Trip trip) {
        return TripDto.builder()
                .withId(trip.getId())
                .withAgencyCode(trip.getAgency().getCode())
                .withSourceStopCode(trip.getSourceStop().getCode())
                .withSourceStopName(trip.getSourceStop().getName())
                .withDestinationStopCode(trip.getDestStop().getCode())
                .withDestinationStopName(trip.getDestStop().getName())
                .withBusCode(trip.getBus().getCode())
                .withJourneyTime(trip.getJourneyTime())
                .withFare(trip.getFare())
                .build();
    }
}
