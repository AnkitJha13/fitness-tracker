package com.fitness.tracker.service;

import com.fitness.tracker.dto.WorkoutDTO;
import com.fitness.tracker.entity.Workout;
import com.fitness.tracker.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Override
    public WorkoutDTO postWorkout(WorkoutDTO workoutDTO){
        Workout workout = new Workout();

        workout.setDate(workoutDTO.getDate());
        workout.setType(workoutDTO.getType());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());

        return workoutRepository.save(workout).workoutDto();
    }

    @Override
    public List<WorkoutDTO> getWorkoutList(){
        List<Workout> workout = workoutRepository.findAll();

        return workout
                .stream()
                .map(Workout::workoutDto).collect(Collectors.toList());
    }
}
















