package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.TrainerSchedule;

import java.time.LocalTime;

public interface TrainerScheduleService {
    TrainerSchedule addSchedule(long idTrainer, String dayOfWeek, LocalTime start, LocalTime end);
    TrainerSchedule getByTrainerId(long idTrainer);
    TrainerSchedule update(long idTrainer, TrainerSchedule trainerSchedule);
    void delete(long idTrainer);
}
