package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.Apprentice;
import com.iaroslaveremeev.model.Trainer;
import com.iaroslaveremeev.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByApprenticeId(long apprenticeId);
    List<Training> findAllByTrainerId(long trainerId);
    boolean existsByApprenticeAndDateAndTimeStart(Apprentice apprentice, LocalDate date, LocalTime timeStart);
    int countByTrainerAndDateAndTimeStart(Trainer trainer, LocalDate date, LocalTime timeStart);
}
