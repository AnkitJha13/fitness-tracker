package com.fitness.tracker.controller;


import com.fitness.tracker.dto.WorkoutDTO;
import com.fitness.tracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/workout")
    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDTO workoutDTO){
        try {
            return ResponseEntity.ok(workoutService.postWorkout(workoutDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New Workout can not get saved");
        }
    }

    @GetMapping("/workout")
    public ResponseEntity<?> getWorkoutList(){
        try {
            return ResponseEntity.ok(workoutService.getWorkoutList());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout list unable to fetch");
        }
    }
}
