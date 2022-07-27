package com.emids.sms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ScreenPlay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "screenplay_id")
    private int id;

    private String name;
    private String genre;
    private String description;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL/*,mappedBy = "screenplay"*/)
    private Set<Writer> writer = new HashSet<>();


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
