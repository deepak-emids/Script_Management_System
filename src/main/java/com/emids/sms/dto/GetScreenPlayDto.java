package com.emids.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GetScreenPlayDto {
    private int id;
    private String name;
    private String genre;
    private String description;
    private List<String> writers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
