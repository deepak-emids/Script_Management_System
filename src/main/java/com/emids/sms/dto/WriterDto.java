package com.emids.sms.dto;

import com.emids.sms.model.Gender;
import com.emids.sms.model.Role;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class WriterDto {

    @NotBlank()
    private String name;

    @NotNull(message = "Age should be valid number")
    @Range(min = 1, max = 100)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password should have min 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
