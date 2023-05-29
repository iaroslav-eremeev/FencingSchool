package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Apprentice;
import java.util.List;

/**
 * •	post – осуществляет добавление нового ученика в базу данных
 * •	get – осуществляет получение всех учеников, а так же ученика по его id
 * •	put – осуществляет обновление ученика по его id
 * •	delete – осуществляет удаление ученика и всех записей, связанных с ним
 */
public interface ApprenticeService {

    void add(Apprentice apprentice);
    List<Apprentice> get();
    Apprentice get(long id);
    Apprentice update(Apprentice apprentice);
    void delete(long id);

}
