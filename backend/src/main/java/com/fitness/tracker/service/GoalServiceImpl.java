package com.fitness.tracker.service;


import com.fitness.tracker.dto.GoalDTO;
import com.fitness.tracker.entity.Goal;
import com.fitness.tracker.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Override
    public GoalDTO saveGoalDTO(GoalDTO goalDTO){
        Goal goal = new Goal();

        goal.setDescription(goalDTO.getDescription());
        goal.setStartDate(goalDTO.getStartDate());
        goal.setEndDate(goalDTO.getEndDate());
        goal.setAchieved(false);

        return goalRepository.save(goal).goalDTO();
    }

    @Override
    public List<GoalDTO> getGoalList(){
        List<Goal> goalList = goalRepository.findAll();
        return goalList
                .stream()
                .map(Goal::goalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GoalDTO updateGoal(Long id){
        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if (optionalGoal.isPresent()){
            Goal goal = optionalGoal.get();

            goal.setAchieved(true);
            return goalRepository.save(goal).goalDTO();
        }
        throw new EntityNotFoundException("Goal not found");
    }

    @Override
    public Goal deleteGoal(Long id){
        Optional<Goal> optionalGoal = goalRepository.findById(id);
        if (optionalGoal.isPresent()){
            goalRepository.deleteById(id);
            return null;
        }
        throw new EntityNotFoundException("Goal not found");
    }
}













