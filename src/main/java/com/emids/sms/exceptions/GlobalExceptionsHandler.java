package com.emids.sms.exceptions;

import com.emids.sms.dto.ExceptionDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(WriterException.class)
    public ResponseEntity<ExceptionDto> handleException(WriterException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScreenPlayException.class)
    public ResponseEntity<ExceptionDto> handleScreenPlayException(ScreenPlayException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationError(MethodArgumentNotValidException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionDto> InvalidFormat(InvalidFormatException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(),
                "Enter Valid Data For Writer.Sample Data,Gender-MALE/FEMALE/OTHER,Role-ADMIN/WRITER,Age-Valid Number,Password and Name Not Empty"), HttpStatus.BAD_REQUEST);
    }
}
