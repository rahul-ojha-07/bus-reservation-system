package com.example.brs.repository.bus;

import com.example.brs.entity.bus.Trip;
import com.example.brs.entity.bus.TripSchedule;
import org.springframework.data.repository.CrudRepository;

public interface TripScheduleRepository extends CrudRepository<TripSchedule, Long> {
    TripSchedule findByTripDetailAndTripDate(Trip tripDetail, String tripDate);
}
