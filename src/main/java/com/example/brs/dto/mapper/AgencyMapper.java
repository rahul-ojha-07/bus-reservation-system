package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.AgencyDto;
import com.example.brs.entity.bus.Agency;

import java.util.stream.Collectors;

public class AgencyMapper {
    public static AgencyDto toAgencyDto(Agency agency) {
        return AgencyDto.builder()
                .withCode(agency.getCode())
                .withName(agency.getName())
                .withBuses(agency.getBuses().stream()
                        .map(BusMapper::toBusDto)
                        .collect(Collectors.toSet()))
                .withDetails(agency.getDetails())
                .withOwner(UserMapper.toUserDto(agency.getOwner()))
                .build();
    }
}
