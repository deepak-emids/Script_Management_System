package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import com.emids.sms.model.Role;
import com.emids.sms.model.RoleType;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class WriterDto {

    @NotNull
    private String name;

    @NotNull(message = "Age should be valid number")
    private Integer age;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Password Should Not Be Empty")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
