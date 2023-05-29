package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.TrainerSchedule;

import java.time.LocalTime;

/**
 * •	post – осуществляет добавление расписания для конкретного тренера
 * (принимает в качестве path айди тренера, аргументы – день недели, время начала и время конца)
 * •	get – осуществляет получение расписания для тренера с заданным id
 * •	put – осуществляет обновление расписания тренера с заданным id
 * •	delete – осуществляет удаление расписания тренера с заданным id
 */

public interface TrainerScheduleService {
    TrainerSchedule addSchedule(long idTrainer, String dayOfWeek, LocalTime start, LocalTime end);
    TrainerSchedule getByTrainerId(long idTrainer);
    TrainerSchedule update(long idTrainer, TrainerSchedule trainerSchedule);
    void delete(long idTrainer);
}
