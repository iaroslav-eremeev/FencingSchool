package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.*;
import com.iaroslaveremeev.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository trainingRepository;
    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void addByApprenticeId(long apprenticeId) {
        try {
            Apprentice apprentice = this.trainingRepository
                    .getTrainingByApprenticeId(apprenticeId).getApprentice();

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
    public void addByTrainerId(long trainerId) {

    }

    @Override
    public Training get(long id) {
        return null;
    }

    @Override
    public List<Training> getByApprenticeId(long apprenticeId) {
        return null;
    }

    @Override
    public List<Training> getByTrainerId(long trainerId) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
