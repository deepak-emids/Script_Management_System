package com.emids.sms.exceptions;

import com.emids.sms.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(WriterException.class)
    public ResponseEntity<ExceptionDto> handleException(WriterException e, WebRequest request) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage(), e.getEType()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ScreenPlayException.class)
    public ResponseEntity<ExceptionDto> handleScreenPlayException(ScreenPlayException e, WebRequest request) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage(), e.getEType()), HttpStatus.CONFLICT);
    }
}
