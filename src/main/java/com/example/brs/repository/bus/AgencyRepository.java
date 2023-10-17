package com.example.brs.repository.bus;


import com.example.brs.entity.bus.Agency;
import com.example.brs.entity.user.User;
import org.springframework.data.repository.CrudRepository;

public interface AgencyRepository extends CrudRepository<Agency, Long> {
    Agency findByCode(String agencyCode);
    Agency findByOwner(User owner);
    Agency findByName(String name);
}
