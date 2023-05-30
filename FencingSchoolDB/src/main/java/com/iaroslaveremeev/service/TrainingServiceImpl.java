package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.*;
import com.iaroslaveremeev.repository.ApprenticeRepository;
import com.iaroslaveremeev.repository.TrainerRepository;
import com.iaroslaveremeev.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private TrainingRepository trainingRepository;
    private TrainerRepository trainerRepository;
    private ApprenticeRepository apprenticeRepository;
    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }
    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }
    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    @Override
    public void add(long apprenticeId, long trainerId, Training training) {
        if (!this.apprenticeRepository.existsById(apprenticeId)){
            throw new IllegalArgumentException("No apprentice with such ID found!");
        }
        if (!this.trainerRepository.existsById(trainerId)){
            throw new IllegalArgumentException("No trainer with such ID found!");
        }
        try {
            Apprentice apprentice = this.apprenticeRepository.getById(apprenticeId);
            // Check if the Apprentice is already scheduled for another Training at the same time
            if (trainingRepository
                    .existsByApprenticeAndDateAndTimeStart(apprentice, training.getDate(), training.getTimeStart())) {
                throw new IllegalArgumentException("The apprentice is already scheduled for another training at this time!");
            }
            Trainer trainer = this.trainerRepository.getById(trainerId);
            // Check if the Trainer has already reached the maximum number of apprentices
            if (trainingRepository
                    .countByTrainerAndDateAndTimeStart(trainer, training.getDate(), training.getTimeStart()) >= 3) {
                throw new IllegalArgumentException("The trainer already has 3 apprentices for this slot!");
            }
            training.setApprentice(apprentice);
            training.setTrainer(trainer);
            trainingRepository.save(training);
        } catch (Exception e) {
            throw new RuntimeException("Training not added!");
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
