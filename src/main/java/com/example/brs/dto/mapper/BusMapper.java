package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.BusDto;
import com.example.brs.entity.bus.Bus;

public class BusMapper {
    public static BusDto toBusDto(Bus bus) {
        return BusDto.builder()
                .withCode(bus.getCode())
                .withCapacity(bus.getCapacity())
                .withMake(bus.getMake())
                .build();
    }
}
