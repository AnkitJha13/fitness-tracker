package com.fitness.tracker.service;

import com.fitness.tracker.dto.WorkoutDTO;

import java.util.List;

public interface WorkoutService {
    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);

    List<WorkoutDTO> getWorkoutList();
}
