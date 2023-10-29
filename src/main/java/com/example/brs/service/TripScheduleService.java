package com.example.brs.service;

import com.example.brs.dto.entity.bus.TripDto;
import com.example.brs.dto.entity.bus.TripScheduleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripScheduleService {
    List<TripScheduleDto> getAvailableTripSchedules(String sourceStopCode, String destinationStopCode, String tripDate);

    TripScheduleDto getTripSchedule(TripDto tripDto, String tripDate, boolean createScheduleForTrip);
}
