package com.emids.sms.exceptions;

import com.emids.sms.dto.ExceptionDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(WriterException.class)
    public ResponseEntity<ExceptionDto> handleException(WriterException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ScreenPlayException.class)
    public ResponseEntity<ExceptionDto> handleScreenPlayException(ScreenPlayException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationError(MethodArgumentNotValidException e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> Exception(Exception e) {
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(new Date(), e.getMessage()), HttpStatus.CONFLICT);
    }
}
