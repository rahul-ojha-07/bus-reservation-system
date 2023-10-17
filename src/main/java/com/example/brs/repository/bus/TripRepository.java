package com.example.brs.repository.bus;

import com.example.brs.entity.bus.Agency;
import com.example.brs.entity.bus.Bus;
import com.example.brs.entity.bus.Stop;
import com.example.brs.entity.bus.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {
    Trip findBySourceStopAndDestStopAndBus(Stop source, Stop destination, Bus bus);
    List<Trip> findAllBySourceStopAndDestStop(Stop source, Stop destination);
    List<Trip> findByAgency(Agency agency);
}
