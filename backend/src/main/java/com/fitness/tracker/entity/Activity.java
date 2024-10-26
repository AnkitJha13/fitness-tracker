package com.fitness.tracker.entity;

import com.fitness.tracker.dto.ActivityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;
    private Date date;
    private int steps;
    private double distance;
    private int caloriesBurned;

    public ActivityDTO getActivityDTO() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(activityId);
        activityDTO.setDate(date);
        activityDTO.setDistance(distance);
        activityDTO.setSteps(steps);
        activityDTO.setCaloriesBurned(caloriesBurned);

        return activityDTO;
    }
}







