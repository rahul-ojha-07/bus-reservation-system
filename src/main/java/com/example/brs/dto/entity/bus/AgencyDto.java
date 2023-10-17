package com.example.brs.dto.entity.bus;


import com.example.brs.dto.entity.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyDto {
    private String code;
    private UserDto owner;
    private Set<BusDto> buses;
    private String name;
    private String details;
}
