package com.example.brs.service;

import com.example.brs.dto.entity.bus.AgencyDto;
import com.example.brs.dto.entity.bus.BusDto;
import com.example.brs.dto.entity.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AgencyService {
    AgencyDto getAgency(UserDto userDto);
    AgencyDto addAgency(AgencyDto agencyDto);
    AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto);
}
