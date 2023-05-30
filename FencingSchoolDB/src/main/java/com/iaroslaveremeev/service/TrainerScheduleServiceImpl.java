package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Trainer;
import com.iaroslaveremeev.model.TrainerSchedule;
import com.iaroslaveremeev.repository.TrainerScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TrainerScheduleServiceImpl implements TrainerScheduleService {
    private TrainerScheduleRepository trainerScheduleRepository;
    @Autowired
    public void setTrainerScheduleRepository(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }
    @Override
    public TrainerSchedule addSchedule(long idTrainer, String dayOfWeek, LocalTime start, LocalTime end) {
        try {
            Trainer trainer = this.trainerScheduleRepository
                    .getTrainerScheduleByTrainerId(idTrainer).getTrainer();
            TrainerSchedule trainerSchedule = trainer.getTrainerSchedule();
            if(trainerSchedule == null){
                trainerSchedule = new TrainerSchedule();
            }
            if (start.isBefore(end)){
                trainerSchedule.add(dayOfWeek, start, end);
                return trainerSchedule;
            }
            else return null;
        } catch (Exception e) {
            throw new RuntimeException("Trainer schedule not added!");
        }
    }

    @Override
    public TrainerSchedule getByTrainerId(long idTrainer) {
        try {
            return this.trainerScheduleRepository.getTrainerScheduleByTrainerId(idTrainer);
        } catch (Exception e){
            throw new IllegalArgumentException("There is no schedule for such trainer ID!");
        }
    }

    @Override
    public TrainerSchedule update(long idTrainer, TrainerSchedule trainerSchedule) {
        try {
            TrainerSchedule trainerScheduleToUpdate = this.trainerScheduleRepository
                    .getTrainerScheduleByTrainerId(idTrainer);
            trainerScheduleToUpdate.setMondayStart(trainerSchedule.getMondayStart());
            trainerScheduleToUpdate.setMondayEnd(trainerSchedule.getMondayEnd());
            trainerScheduleToUpdate.setTuesdayStart(trainerSchedule.getTuesdayStart());
            trainerScheduleToUpdate.setTuesdayEnd(trainerSchedule.getTuesdayEnd());
            trainerScheduleToUpdate.setWednesdayStart(trainerSchedule.getWednesdayStart());
            trainerScheduleToUpdate.setWednesdayEnd(trainerSchedule.getWednesdayEnd());
            trainerScheduleToUpdate.setThursdayStart(trainerSchedule.getThursdayStart());
            trainerScheduleToUpdate.setThursdayEnd(trainerSchedule.getThursdayEnd());
            trainerScheduleToUpdate.setFridayStart(trainerSchedule.getFridayStart());
            trainerScheduleToUpdate.setFridayEnd(trainerSchedule.getFridayEnd());
            trainerScheduleToUpdate.setSaturdayStart(trainerSchedule.getSaturdayStart());
            trainerScheduleToUpdate.setSaturdayEnd(trainerSchedule.getSaturdayEnd());
            trainerScheduleToUpdate.setSundayStart(trainerSchedule.getSundayStart());
            trainerScheduleToUpdate.setSundayEnd(trainerSchedule.getSundayEnd());
            this.trainerScheduleRepository.save(trainerScheduleToUpdate);
            return trainerScheduleToUpdate;
        } catch (Exception e){
            throw new IllegalArgumentException("Trainer schedule update failed!");
        }
    }

    @Override
    public void delete(long idTrainer) {
        try {
            this.trainerScheduleRepository.deleteById(idTrainer);
        } catch (Exception e){
            throw new IllegalArgumentException("No trainer with such ID found!");
        }
    }
}
