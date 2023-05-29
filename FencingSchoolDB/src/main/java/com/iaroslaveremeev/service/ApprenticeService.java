package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Apprentice;
import java.util.List;

public interface ApprenticeService {

    void add(Apprentice apprentice);
    List<Apprentice> get();
    Apprentice get(long id);
    Apprentice update(long id, Apprentice apprentice);
    void delete(long id);

}
