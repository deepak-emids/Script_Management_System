package com.emids.sms.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WriterException extends RuntimeException {
    String message;
    ExceptionType eType;

    public WriterException(String message, ExceptionType eType) {
        this.message = message;
        this.eType = eType;
    }
}
