package com.example.brs.service.impl;

import com.example.brs.dto.entity.bus.TripDto;
import com.example.brs.dto.entity.bus.TripScheduleDto;
import com.example.brs.dto.mapper.TripMapper;
import com.example.brs.dto.mapper.TripScheduleMapper;
import com.example.brs.entity.bus.Stop;
import com.example.brs.entity.bus.Trip;
import com.example.brs.entity.bus.TripSchedule;
import com.example.brs.exception.EntityType;
import com.example.brs.exception.ExceptionType;
import com.example.brs.exception.StandardExceptionMessage;
import com.example.brs.repository.bus.StopRepository;
import com.example.brs.repository.bus.TripRepository;
import com.example.brs.repository.bus.TripScheduleRepository;
import com.example.brs.service.TripScheduleService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class TripScheduleServiceImpl implements TripScheduleService, StandardExceptionMessage {

    private final StopRepository stopRepository;
    private final TripRepository tripRepository;
    private final TripScheduleRepository tripScheduleRepository;
    /**
     * @param sourceStopCode
     * @param destinationStopCode
     * @param tripDate
     * @return
     */
    @Override
    public List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode, String tripDate) {
        Stop sourceStop = getStop(sourceStopCode);
        Stop destinationStop = getStop(destinationStopCode);

        List<Trip> trips = tripRepository.findAllBySourceStopAndDestStop(sourceStop, destinationStop);

        return trips.stream()
                .map(trip -> getTripSchedule(TripMapper.toTripDto(trip), tripDate, true))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * @param tripDto
     * @param tripDate
     * @param createScheduleForTrip
     * @return
     */
    @Override
    public TripScheduleDto getTripSchedule(TripDto tripDto, String tripDate, boolean createScheduleForTrip) {
        Trip trip = tripRepository.findById(tripDto.getId())
                .orElseThrow(() -> exception(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(tripDto.getId())));
        Optional<TripSchedule> tripSchedule = Optional.ofNullable(tripScheduleRepository.findByTripDetailAndTripDate(trip, tripDate));
        if (tripSchedule.isPresent()) {
            return TripScheduleMapper.toTripScheduleDto(tripSchedule.get());
        } else {
            if (createScheduleForTrip) {
                TripSchedule tripSchedule1 = TripSchedule.builder()
                        .withTripDetails(trip)
                        .withTripDate(tripDate)
                        .withAvailableSeats(trip.getBus().getCapacity())
                        .build();

                return TripScheduleMapper.toTripScheduleDto(tripSchedule1);
            } else {
                throw exceptionWithId(EntityType.TRIP, ExceptionType.ENTITY_NOT_FOUND, 2L,String.valueOf(tripDto.getId()), tripDate);
            }
        }
    }

    private Stop getStop(String stopCode) {
        return Optional.ofNullable(stopRepository.findByCode(stopCode))
                .orElseThrow(() -> exception(EntityType.STOP, ExceptionType.ENTITY_NOT_FOUND, stopCode));
    }
}
