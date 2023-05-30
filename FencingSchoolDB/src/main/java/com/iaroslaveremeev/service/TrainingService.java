package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Training;
import java.util.List;

public interface TrainingService {
    void addByApprenticeId(long apprenticeId, Training training);
    void addByTrainerId(long trainerId, Training training);
    Training get(long id);
    List<Training> getByApprenticeId(long apprenticeId);
    List<Training> getByTrainerId(long trainerId);
    void delete(long id);

}
