package com.fitness.tracker.service;

import com.fitness.tracker.dto.GraphDTO;
import com.fitness.tracker.dto.StatsDTO;

public interface StatsService {
    StatsDTO getStats();

    GraphDTO getGraphStats();
}
