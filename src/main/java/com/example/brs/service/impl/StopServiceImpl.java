package com.example.brs.service.impl;

import com.example.brs.dto.entity.bus.StopDto;
import com.example.brs.dto.mapper.StopMapper;
import com.example.brs.entity.bus.Stop;
import com.example.brs.exception.EntityType;
import com.example.brs.exception.ExceptionType;
import com.example.brs.exception.StandardExceptionMessage;
import com.example.brs.repository.bus.StopRepository;
import com.example.brs.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StopServiceImpl implements StopService, StandardExceptionMessage {

    private final StopRepository stopRepository;


    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }
    /**
     * @return
     */
    @Override
    public Set<StopDto> getAllStops() {
        return StreamSupport.stream(stopRepository.findAll().spliterator(), false)
                .map(StopMapper::toStopDto)
                .collect(Collectors.toSet());
    }

    /**
     * @param stopCode
     * @return
     */
    @Override
    public StopDto getStopByCode(String stopCode) {
        Optional<Stop> stop = Optional.ofNullable(stopRepository.findByCode(stopCode));
        return stop.map(StopMapper::toStopDto)
                .orElseThrow(() -> exception(EntityType.STOP,ExceptionType.ENTITY_NOT_FOUND,stopCode));

    }
}
