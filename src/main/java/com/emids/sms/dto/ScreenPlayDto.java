package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class ScreenPlayDto {
    @NotNull
    public String name;

    @NotNull
    public String genre;

    @NotNull
    public String description;
}
