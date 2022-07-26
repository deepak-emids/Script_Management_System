package com.emids.sms.dto;

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

    public ExceptionDto(Date timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }
}
