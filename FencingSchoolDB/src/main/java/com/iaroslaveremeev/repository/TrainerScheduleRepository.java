package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.TrainerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long> {
    TrainerSchedule getTrainerScheduleByTrainerId(long idTrainer);
}
