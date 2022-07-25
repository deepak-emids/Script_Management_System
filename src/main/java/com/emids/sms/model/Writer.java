package com.emids.sms.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
@ToString
@NoArgsConstructor
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Integer age;
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
