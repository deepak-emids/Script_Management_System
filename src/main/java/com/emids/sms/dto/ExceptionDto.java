package com.emids.sms.dto;

import com.emids.sms.exceptions.ExceptionType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
@Getter
@Setter
public class ExceptionDto {
    private Date timestamp;
    private String message;
    private ExceptionType details;

    public ExceptionDto(Date timestamp, String message, ExceptionType details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
