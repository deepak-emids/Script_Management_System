package com.emids.sms.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Integer age;
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "writer_screenplay",
            joinColumns = {@JoinColumn(name = "writer_id")},
            inverseJoinColumns = {@JoinColumn(name = "screenplay_id")})
    private Set<ScreenPlay> screenplay = new HashSet<>();
}
