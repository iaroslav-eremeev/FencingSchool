package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Trainer;
import com.iaroslaveremeev.model.TrainerSchedule;
import com.iaroslaveremeev.repository.TrainerRepository;
import com.iaroslaveremeev.repository.TrainerScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TrainerScheduleServiceImpl implements TrainerScheduleService {
    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerRepository trainerRepository;
    @Autowired
    public void setTrainerScheduleRepository(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }
    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }
    @Override
    public TrainerSchedule addSchedule(long idTrainer, String dayOfWeek, LocalTime start, LocalTime end) {
        Trainer trainer = this.trainerRepository.getById(idTrainer);
        TrainerSchedule trainerSchedule = trainer.getTrainerSchedule();
        if(trainerSchedule == null){
            trainerSchedule = new TrainerSchedule();
        }
        if (start.isBefore(end)){
            trainerSchedule.add(dayOfWeek, start, end);
            return trainerSchedule;
        }
        else return null;
    }
}
