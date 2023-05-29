package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Trainer;
import com.iaroslaveremeev.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }


    @Override
    public void add(Trainer trainer) {
        try {
            this.trainerRepository.save(trainer);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Trainer addition failed!");
        }
    }

    @Override
    public List<Trainer> get() {
        try {
            return this.trainerRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("The trainer list is not retrieved!");
        }
    }

    @Override
    public Trainer get(long id) {
        return this.trainerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Trainer with such ID does not exist!"));
    }

    @Override
    public Trainer update(long id, Trainer trainer) {
        try {
            Trainer trainerToUpdate = this.trainerRepository.getById(id);
            trainerToUpdate.setName(trainer.getName());
            trainerToUpdate.setSurname(trainer.getSurname());
            trainerToUpdate.setPatronymic(trainer.getPatronymic());
            trainerToUpdate.setExperience(trainer.getExperience());
            this.trainerRepository.save(trainerToUpdate);
            return trainerToUpdate;
        } catch (Exception e){
            throw new IllegalArgumentException("Trainer update failed!");
        }
    }

    @Override
    public void delete(long id) {
        try {
            this.trainerRepository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("No trainer with such ID found!");
        }
    }
}
