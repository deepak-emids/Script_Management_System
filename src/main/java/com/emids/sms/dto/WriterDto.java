package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import com.emids.sms.model.Role;
import com.emids.sms.model.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Data
public class WriterDto {
    @NotNull
    public String name;

    @NotNull
    public int age;

    @NotNull
    public Gender gender;

    public Set<Role> roles;
}
