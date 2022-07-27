package com.emids.sms.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
public class Writer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "writer_id")
    private int id;

    @NotBlank()
    @Column(updatable = false)
    private String name;

    @NotNull(message = "Age should be valid number")
    @Range(min = 1, max = 100)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank()
    @Size(min = 4, message = "Password should have min 6 characters")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ScreenPlay> screenplay = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;
}
