package com.example.brs.dto.entity.bus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripScheduleDto {
    private Long id;
    private Long tripId;
    private String tripDate;
    private Integer availableSeats;
    private Double fare;
    private Integer journeyTime;
    private String busCode;
    private String sourceStop;
    private String destinationStop;
}
