package com.fitness.tracker.controller;

import com.fitness.tracker.dto.GoalDTO;
import com.fitness.tracker.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/goals")
    public ResponseEntity<?> addNewGoal(@RequestBody GoalDTO goalDTO){
        try {
            return ResponseEntity.ok(goalService.saveGoalDTO(goalDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New goal can not added");
        }
    }

    @GetMapping("/goals")
    public ResponseEntity<?> getAllGoal(){
        try {
            return ResponseEntity.ok(goalService.getGoalList());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Goal list not found");
        }
    }

    @GetMapping("/goals/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id){
        try {
            return ResponseEntity.ok(goalService.updateGoal(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Goal updating failed");
        }
    }

    @DeleteMapping("/goals/{id}")
    public ResponseEntity<?> deleteGoalById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(goalService.deleteGoal(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Goal "+id+" can not deleted");
        }
    }
}