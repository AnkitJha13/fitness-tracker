package com.fitness.tracker.service;

import com.fitness.tracker.dto.GoalDTO;
import com.fitness.tracker.entity.Goal;

import java.util.List;

public interface GoalService {

    GoalDTO saveGoalDTO(GoalDTO goalDTO);

    List<GoalDTO> getGoalList();

    GoalDTO updateGoal(Long id);

    Goal deleteGoal(Long id);
}
