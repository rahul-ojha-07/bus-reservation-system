package com.example.brs.service.impl;

import com.example.brs.dto.entity.bus.TripDto;
import com.example.brs.dto.mapper.TripMapper;
import com.example.brs.entity.bus.Agency;
import com.example.brs.entity.bus.Bus;
import com.example.brs.entity.bus.Stop;
import com.example.brs.entity.bus.Trip;
import com.example.brs.exception.EntityType;
import com.example.brs.exception.ExceptionType;
import com.example.brs.exception.StandardExceptionMessage;
import com.example.brs.repository.bus.AgencyRepository;
import com.example.brs.repository.bus.BusRepository;
import com.example.brs.repository.bus.StopRepository;
import com.example.brs.repository.bus.TripRepository;
import com.example.brs.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TripServiceImpl implements TripService, StandardExceptionMessage {


    private final TripRepository tripRepository;
    private final StopRepository stopRepository;
    private final AgencyRepository agencyRepository;
    private final BusRepository busRepository;



    /**
     * @param tripId
     * @return
     */
    @Override
    public TripDto getTripById(Long tripId) {
        return TripMapper.toTripDto(tripRepository.findById(tripId)
                .orElseThrow(() -> exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(tripId))));
    }


    private Stop getStop(String stopCode) {
        return Optional.ofNullable(stopRepository.findByCode(stopCode))
                .orElseThrow(() -> exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, stopCode));
    }



    /**
     * @param tripDto
     * @return
     */
    @Override
    @Transactional
    public List<TripDto> addTrip(TripDto tripDto) {
        Stop sourceStop = getStop(tripDto.getSourceStopCode());
        Stop destinationStop = getStop(tripDto.getDestinationStopCode());

        if (sourceStop.getCode().equals(destinationStop.getCode())) {
            Agency agency = Optional.of(agencyRepository.findByCode(tripDto.getAgencyCode()))
                    .orElseThrow(() -> exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, tripDto.getAgencyCode()));

            Bus bus = Optional.of(busRepository.findByCode(tripDto.getBusCode()))
                    .orElseThrow(() -> exception(EntityType.BUS, ExceptionType.ENTITY_NOT_FOUND, tripDto.getBusCode()));

            List<TripDto> trips = new ArrayList<>(2);
            Trip toTrip = Trip.builder()
                    .withSourceStop(sourceStop)
                    .withDestStop(destinationStop)
                    .withAgency(agency)
                    .withBus(bus)
                    .withJourneyTime(tripDto.getJourneyTime())
                    .withFare(tripDto.getFare())
                    .build();
            trips.add(TripMapper.toTripDto(tripRepository.save(toTrip)));
            Trip fromTrip = Trip.builder()
                    .withSourceStop(destinationStop)
                    .withDestStop(sourceStop)
                    .withAgency(agency)
                    .withBus(bus)
                    .withJourneyTime(tripDto.getJourneyTime())
                    .withFare(tripDto.getFare())
                    .build();
            trips.add(TripMapper.toTripDto(tripRepository.save(fromTrip)));
            return trips;
        }
        throw exception(EntityType.TRIP, ExceptionType.ENTITY_EXCEPTION, "");
    }

    /**
     * @param agencyCode
     * @return
     */
    @Override
    public List<TripDto> getAgencyTrips(String agencyCode) {
        Agency agency = Optional.of(agencyRepository.findByCode(agencyCode))
                .orElseThrow(() -> exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, agencyCode));

        List<Trip> agencyTrips = tripRepository.findByAgency(agency);
//        if (agencyTrips.isEmpty())
//            return Collections.emptyList();

        return agencyTrips.stream()
                .map(TripMapper::toTripDto)
                .collect(Collectors.toList());
    }

    /**
     * @param sourceStopCode
     * @param destinationStopCode
     * @return
     */
    @Override
    public List<TripDto> getAvailableTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        List<Trip> availableTrips = findTripsBetweenStops(sourceStopCode, destinationStopCode);
        return availableTrips.stream()
                .map(TripMapper::toTripDto)
                .collect(Collectors.toList());
    }


    private List<Trip> findTripsBetweenStops(String sourceStopCode, String destinationStopCode) {
        Stop sourceStop = getStop(sourceStopCode);
        Stop destinationStop = getStop(destinationStopCode);

        return tripRepository.findAllBySourceStopAndDestStop(sourceStop, destinationStop);
    }
}
