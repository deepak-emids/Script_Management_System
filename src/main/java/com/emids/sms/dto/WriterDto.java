package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class WriterDto {
    @NotNull
    public String name;

    @NotNull
    public int age;

    public Gender gender;
}
