package com.saber.multiplication.multiplicationv2.adviser;

import com.saber.multiplication.multiplicationv2.dto.ServerValidationDto;
import com.saber.multiplication.multiplicationv2.dto.ServiceErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("Error for BAD_REQUEST for uri {} ===> {}", request.getContextPath(), ex.getMessage());

        ServiceErrorResponse errorResponse = new ServiceErrorResponse();

        errorResponse.setCode(status.value());
        errorResponse.setMessage(status.toString());

        List<ServerValidationDto> validationDtoList = new ArrayList<>();

        ServerValidationDto validationDtoClone = new ServerValidationDto();


        for (FieldError allError : ex.getBindingResult().getFieldErrors()) {

            ServerValidationDto validationDto = validationDtoClone.clone();

            validationDto.setFieldName(allError.getField());
            validationDto.setValidationMessage(allError.getDefaultMessage());

            validationDtoList.add(validationDto);
        }
        errorResponse.setValidationDetails(validationDtoList);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("Error for BAD_REQUEST for uri {} ===> {}", request.getContextPath(), ex.getMessage());

        ServiceErrorResponse errorResponse = new ServiceErrorResponse();

        errorResponse.setCode(status.value());
        errorResponse.setMessage(status.toString());

        List<ServerValidationDto> validationDtoList = new ArrayList<>();

        ServerValidationDto validationDto = new ServerValidationDto();

        validationDto.setFieldName(ex.getParameterName());
        validationDto.setValidationMessage(ex.getMessage());

        validationDtoList.add(validationDto);

        errorResponse.setValidationDetails(validationDtoList);

        return ResponseEntity.status(status).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Error for BAD_REQUEST for uri {} ===> {}", request.getContextPath(), ex.getMessage());

        ServiceErrorResponse errorResponse = new ServiceErrorResponse();

        errorResponse.setCode(status.value());
        errorResponse.setMessage(status.toString());

        List<ServerValidationDto> validationDtoList = new ArrayList<>();

        ServerValidationDto validationDto = new ServerValidationDto();

        validationDto.setFieldName(ex.getHttpInputMessage().toString());
        validationDto.setValidationMessage(ex.getMessage());

        validationDtoList.add(validationDto);

        errorResponse.setValidationDetails(validationDtoList);

        return ResponseEntity.status(status).body(errorResponse);
    }

}
