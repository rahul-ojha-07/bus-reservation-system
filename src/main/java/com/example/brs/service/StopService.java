package com.example.brs.service;

import com.example.brs.dto.entity.bus.StopDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface StopService {
    /**
     * @return
     */
    Set<StopDto> getAllStops();

    /**
     * @param stopCode
     * @return
     */
    StopDto getStopByCode(String stopCode);
}
