package com.emids.sms.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
@ToString
@NoArgsConstructor
public class ScreenPlay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String genre;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
