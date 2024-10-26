package com.fitness.tracker.service;

import com.fitness.tracker.dto.ActivityDTO;
import com.fitness.tracker.entity.Activity;
import com.fitness.tracker.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;

    @Override
    public ActivityDTO postActivity(ActivityDTO dto){
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        return activityRepository.save(activity).getActivityDTO();
    }

    @Override
    public List<ActivityDTO> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        return activities
                .stream()
                .map(Activity::getActivityDTO)
                .collect(Collectors.toList());
    }
}
