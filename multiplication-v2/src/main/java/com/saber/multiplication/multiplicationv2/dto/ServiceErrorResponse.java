package com.saber.multiplication.multiplicationv2.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.util.List;

@Data
public class ServiceErrorResponse {
    private Integer code;
    private String message;
    @JsonRawValue
    private Object originalMessage;
    private List<ServerValidationDto> validationDetails;
}
