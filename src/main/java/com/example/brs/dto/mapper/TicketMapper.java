package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.TicketDto;
import com.example.brs.entity.bus.Ticket;

public class TicketMapper {
    public static TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .withId(ticket.getId())
                .withBusCode(ticket.getTripSchedule().getTrip().getBus().getCode())
                .withSeatNumber(ticket.getSeatNumber())
                .withSourceStop(ticket.getTripSchedule().getTrip().getSourceStop().getName())
                .withDestinationStop(ticket.getTripSchedule().getTrip().getDestStop().getName())
                .withCancellable(ticket.getCancellable())
                .withJourneyDate(ticket.getJourneyDate())
                .withPassengerName(ticket.getPassenger().getFullName())
                .withPassengerMobileNumber(ticket.getPassenger().getMobileNumber())
                .build();
    }
}
