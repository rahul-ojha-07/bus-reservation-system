package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.bus.TicketDto;
import com.example.brs.entity.bus.Ticket;
import com.example.brs.entity.bus.Trip;

public class TicketMapper {
    public static TicketDto toTicketDto(Ticket ticket) {
        Trip trip = ticket.getTripSchedule().getTripDetails();
        return TicketDto.builder()
                .withId(ticket.getId())
                .withBusCode(trip.getBus().getCode())
                .withSeatNumber(ticket.getSeatNumber())
                .withSourceStop(trip.getSourceStop().getName())
                .withDestinationStop(trip.getDestStop().getName())
                .withCancellable(ticket.getCancellable())
                .withJourneyDate(ticket.getJourneyDate())
                .withPassengerName(ticket.getPassenger().getFullName())
                .withPassengerMobileNumber(ticket.getPassenger().getMobileNumber())
                .build();
    }
}
