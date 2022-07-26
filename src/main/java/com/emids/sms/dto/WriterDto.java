package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import com.emids.sms.model.Role;
import com.emids.sms.model.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Data
@ToString
public class WriterDto {

    public String name;
    public int age;
    public Gender gender;
    public String password;
    public Set<Role> roles;
}
