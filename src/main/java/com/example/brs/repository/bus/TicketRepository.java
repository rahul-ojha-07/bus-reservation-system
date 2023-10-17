package com.example.brs.repository.bus;

import com.example.brs.entity.bus.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
