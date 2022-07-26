package com.emids.sms.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType role;
}