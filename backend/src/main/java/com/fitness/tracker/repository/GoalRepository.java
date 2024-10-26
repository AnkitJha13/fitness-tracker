package com.fitness.tracker.repository;

import com.fitness.tracker.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = true")
    Long countAchievedGoals();

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = false")
    Long countNotAchievedGoals();
}
