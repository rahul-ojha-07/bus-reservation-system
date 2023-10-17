package com.example.brs.repository.bus;

import com.example.brs.entity.bus.Agency;
import com.example.brs.entity.bus.Bus;
import org.springframework.data.repository.CrudRepository;

public interface BusRepository extends CrudRepository<Bus, Long> {
    Bus findByCode(String busCode);
    Bus findByCodeAndAgency(String busCode, Agency agency);
}
