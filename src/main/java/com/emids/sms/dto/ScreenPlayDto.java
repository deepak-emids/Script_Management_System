package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class ScreenPlayDto {
    @NotEmpty
    public String name;

    public String genre;
    
    public String description;
}
