package com.example.brs.dto.entity.bus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopDto implements Comparable<StopDto>{
    private String code;
    private String name;
    private String detail;

    /**
     *
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(StopDto other) {
        return this.getName().compareTo(other.getName());
    }
}
