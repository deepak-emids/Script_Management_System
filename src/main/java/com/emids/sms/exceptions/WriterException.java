package com.emids.sms.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WriterException extends RuntimeException {
    String message;
    public WriterException(String message) {
        this.message = message;
    }
}
