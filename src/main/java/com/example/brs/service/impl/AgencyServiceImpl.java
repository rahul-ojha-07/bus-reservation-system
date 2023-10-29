package com.example.brs.service.impl;

import com.example.brs.dto.entity.bus.AgencyDto;
import com.example.brs.dto.entity.bus.BusDto;
import com.example.brs.dto.entity.user.UserDto;
import com.example.brs.dto.mapper.AgencyMapper;
import com.example.brs.entity.bus.Agency;
import com.example.brs.entity.bus.Bus;
import com.example.brs.entity.user.User;
import com.example.brs.exception.EntityType;
import com.example.brs.exception.ExceptionType;
import com.example.brs.exception.StandardExceptionMessage;
import com.example.brs.repository.bus.AgencyRepository;
import com.example.brs.repository.bus.BusRepository;
import com.example.brs.repository.user.UserRepository;
import com.example.brs.service.AgencyService;
import com.example.brs.service.UserService;
import com.example.brs.util.RandomStringUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class AgencyServiceImpl implements AgencyService, StandardExceptionMessage {

    private final AgencyRepository agencyRepository;

    private final UserRepository userRepository;

    private final BusRepository busRepository;
    public AgencyServiceImpl(AgencyRepository agencyRepository, UserRepository userRepository, BusRepository busRepository) {
        this.agencyRepository = agencyRepository;
        this.userRepository = userRepository;
        this.busRepository = busRepository;
    }

    /**
     * @param userDto
     * @return
     */
    @Override
    public AgencyDto getAgency(UserDto userDto) {
        String email = userDto.getEmail();
        User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(userDto)));

        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByOwner(user));

        return agency.map(AgencyMapper::toAgencyDto)
                .orElseThrow(() -> exceptionWithId(EntityType.AGENCY,ExceptionType.ENTITY_NOT_FOUND,2L,String.valueOf(user)));
    }

    /**
     * @param agencyDto
     * @return
     */
    @Override
    public AgencyDto addAgency(AgencyDto agencyDto) {
        String email = agencyDto.getOwner().getEmail();
        User owner = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> exception(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(agencyDto)));

        Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByOwner(owner));

        if (agency.isPresent())
            throw exception(EntityType.AGENCY,ExceptionType.DUPLICATE_ENTITY,String.valueOf(agency));

        Agency newAgency = Agency.builder()
                .withName(agencyDto.getName())
                .withDetails(agencyDto.getDetails())
                .withOwner(owner)
                .withCode(RandomStringUtil.getAlphaNumericString(8,agencyDto.getName()))
                .build();
        agencyRepository.save(newAgency);
        return AgencyMapper.toAgencyDto(newAgency);
    }

    /**
     * @param agencyDto
     * @param busDto
     * @return
     */
    @Override
    @Transactional
    public AgencyDto updateAgency(AgencyDto agencyDto, BusDto busDto) {
        Agency agency = Optional.ofNullable(agencyRepository.findByCode(agencyDto.getCode()))
                .orElseThrow(() -> exceptionWithId(EntityType.AGENCY, ExceptionType.ENTITY_NOT_FOUND,2L, String.valueOf(agencyDto)));
        if (busDto != null) {
            Optional<Bus> bus = Optional.ofNullable(busRepository.findByCodeAndAgency(busDto.getCode(),agency));

            if (bus.isPresent())
                throw exceptionWithId(EntityType.BUS, ExceptionType.DUPLICATE_ENTITY, 2L, busDto.getCode(), agencyDto.getCode());

            Bus newBus = Bus.builder()
                    .withAgency(agency)
                    .withCode(busDto.getCode())
                    .withCapacity(busDto.getCapacity())
                    .withMake(busDto.getMake())
                    .build();

            busRepository.save(newBus);

            if (agency.getBuses() == null) {
                agency.setBuses(new HashSet<>());
            }
            agency.getBuses().add(newBus);
        } else {
            agency.setName(agency.getName());
            agency.setDetails(agency.getDetails());
        }
        return AgencyMapper.toAgencyDto(agencyRepository.save(agency));
    }
}
