package com.example.brs.repository.bus;

import com.example.brs.entity.bus.Stop;
import org.springframework.data.repository.CrudRepository;

public interface StopRepository extends CrudRepository<Stop, Long> {
    Stop findByCode(String code);
}
