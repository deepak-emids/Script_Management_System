package com.emids.sms.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ScreenPlayException extends RuntimeException {
    String message;
    public ScreenPlayException(String message) {
        this.message = message;
    }
}
