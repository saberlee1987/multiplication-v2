package com.saber.multiplication.multiplicationv2.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.multiplication.multiplicationv2.dto.MessageDto;
import com.saber.multiplication.multiplicationv2.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
public class WelComeController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> welcome(@RequestParam(name = "firstName")
                                              @Valid
                                              @NotBlank(message = "firstName is Required")
                                                      String firstName,
                                              @RequestParam(name = "lastName")
                                              @Valid
                                              @NotBlank(message = "lastName is Required")
                                                      String lastName) {

      log.info("Request for welcome with firstName {} , lastName {}",firstName,lastName);
        String message = String.format("Welcome %s %s",firstName,lastName);
        MessageDto messageDto =new MessageDto();
        messageDto.setMessage(message);
        try {
            log.info("Response for welcome ====> {}",objectMapper.writeValueAsString(messageDto));
        }catch (Exception ex){
            log.error("Error for write log response method ");
        }
        return ResponseEntity.ok(messageDto);
    }

    @PostMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> hello(@RequestBody @Valid @NotNull(message = "person is Required") PersonDto personDto) {

        log.info("Request for welcome with body {}",personDto);
        String message = String.format("Hello %s %s",personDto.getFirstName(),personDto.getLastName());
        MessageDto messageDto =new MessageDto();
        messageDto.setMessage(message);
        try {

            log.info("Response for hello ====> {}",objectMapper.writeValueAsString(messageDto));
        }catch (Exception ex){
            log.error("Error for write log response method ");
        }

        return ResponseEntity.ok(messageDto);
    }
}
