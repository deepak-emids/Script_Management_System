package com.emids.sms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
public class ResponseDto {
    private Integer status;
    private Object data;
    private Object error;
    private Object message;
}
