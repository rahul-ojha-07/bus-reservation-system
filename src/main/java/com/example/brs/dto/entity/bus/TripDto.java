package com.example.brs.dto.entity.bus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDto {
    private Long id;
    private Double fare;
    private Integer journeyTime;
    private String sourceStopCode;
    private String sourceStopName;
    private String destinationStopCode;
    private String destinationStopName;
    private String busCode;
    private String agencyCode;
}
