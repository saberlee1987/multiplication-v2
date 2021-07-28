package com.saber.multiplication.multiplicationv2.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    @NotBlank(message = "firstName is Required")
    private String firstName;
    @NotBlank(message = "lastName is Required")
    private String lastName;
}
