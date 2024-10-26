package com.fitness.tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalDTO {
    private Long id;
    private String description;
    private LocalDate startDate; // Use LocalDate instead of Date
    private LocalDate endDate; // Use LocalDate instead of Date
    private boolean achieved;
}
