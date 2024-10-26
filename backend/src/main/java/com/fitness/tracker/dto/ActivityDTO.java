package com.fitness.tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDTO {

    private Long activityId;
    private Date date;
    private int steps;
    private double distance;
    private int caloriesBurned;
}
