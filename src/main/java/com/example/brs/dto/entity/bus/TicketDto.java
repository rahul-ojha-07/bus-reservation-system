package com.example.brs.dto.entity.bus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDto {
    private Long id;
    private String busCode;
    private Integer seatNumber;
    private Boolean cancellable;
    private String journeyDate;
    private String sourceStop;
    private String destinationStop;
    private String passengerName;
    private String passengerMobileNumber;
}
