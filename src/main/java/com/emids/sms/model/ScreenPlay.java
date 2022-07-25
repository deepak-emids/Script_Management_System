package com.emids.sms.model;


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
    private int id;

    private String name;
    private String genre;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "screenplay")
    private Set<Writer> writer = new HashSet<>();

    public Set<Writer> getWriter() {
        return writer;
    }

    public void setWriter(Set<Writer> writer) {
        this.writer = writer;
    }
}
