package com.fitness.tracker.service;

import com.fitness.tracker.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO postActivity(ActivityDTO dto);

    List<ActivityDTO> getActivities();
}
