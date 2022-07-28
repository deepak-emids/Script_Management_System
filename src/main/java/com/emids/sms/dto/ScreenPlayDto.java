package com.emids.sms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class ScreenPlayDto {
    @NotBlank()
    public String name;

    public String genre;
    
    public String description;
}
