package com.example.brs.dto.entity.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private Boolean isAdmin;
    private Collection<RoleDto> roles;

    public String getFullName() {
        return firstName != null ? firstName + " " + lastName : "";
    }
}
