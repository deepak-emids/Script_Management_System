package com.emids.sms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class ScreenPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "screenplay_id")
    private int id;

    private String name;
    private String genre;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Writer> writer = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
