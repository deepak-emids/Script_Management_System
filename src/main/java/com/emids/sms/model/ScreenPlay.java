package com.emids.sms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class ScreenPlay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "screenplay_id")
    private int id;

    @NotBlank()
    private String name;

    private String genre;
    private String description;

    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH/*,mappedBy = "screenplay"*/)
    private Set<Writer> writer = new HashSet<>();

    @Transient
    private List<String> writers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
