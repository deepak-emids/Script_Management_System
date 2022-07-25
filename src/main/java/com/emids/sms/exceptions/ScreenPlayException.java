package com.emids.sms.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ScreenPlayException extends RuntimeException {
    String message;
    ExceptionType eType;

    public ScreenPlayException(String message, ExceptionType eType) {
        this.message = message;
        this.eType = eType;
    }
}
