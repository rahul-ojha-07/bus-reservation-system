package com.example.brs.service;

import com.example.brs.dto.entity.bus.TicketDto;
import com.example.brs.dto.entity.bus.TripScheduleDto;
import com.example.brs.dto.entity.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto passenger);
}
