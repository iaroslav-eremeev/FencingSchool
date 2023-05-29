package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Trainer;
import java.util.List;

public interface TrainerService {

    void add(Trainer trainer);

    List<Trainer> get();

    Trainer get(long id);
    Trainer update(long id, Trainer trainer);
    void delete(long id);
}
