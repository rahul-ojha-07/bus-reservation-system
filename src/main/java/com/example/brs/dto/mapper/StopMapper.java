package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.StopDto;
import com.example.brs.entity.bus.Stop;

public class StopMapper {
    public static StopDto toStopDto(Stop stop) {
        return StopDto.builder()
                .withCode(stop.getCode())
                .withDetail(stop.getDetails())
                .withName(stop.getName())
                .build();
    }
}
