package com.fitness.tracker.controller;

import com.fitness.tracker.dto.ActivityDTO;
import com.fitness.tracker.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO activityDTO){
        ActivityDTO createActivityDto = activityService.postActivity(activityDTO);

        if (createActivityDto != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivityDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/activity")
    public ResponseEntity<?> getActivityList(){
        try {
            return ResponseEntity.ok(activityService.getActivities());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }
}
