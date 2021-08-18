package com.saber.gimificationv2.dto;

import lombok.Data;

@Data
public class ServerValidationDto implements Cloneable {
    private String fieldName;
    private String validationMessage;

    @Override
    public ServerValidationDto clone() {
        try {
            return (ServerValidationDto) super.clone();
        } catch (Exception ex) {
            return null;
        }
    }
}
