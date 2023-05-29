package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Apprentice;
import com.iaroslaveremeev.repository.ApprenticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeServiceImpl implements ApprenticeService {

    private ApprenticeRepository apprenticeRepository;

    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository){
        this.apprenticeRepository = apprenticeRepository;
    }


    @Override
    public void add(Apprentice apprentice) {
        try {
            this.apprenticeRepository.save(apprentice);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Apprentice addition failed!");
        }
    }

    @Override
    public List<Apprentice> get() {
        try {
            return this.apprenticeRepository.findAll();
        } catch (Exception e){
            throw new RuntimeException("The apprentice list is not retrieved!");
        }
    }

    @Override
    public Apprentice get(long id) {
        return this.apprenticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Apprentice with such ID does not exist!"));
    }

    @Override
    public Apprentice update(Apprentice apprentice) {
        try {
            Apprentice apprenticeToUpdate = this.apprenticeRepository.getById(apprentice.getId());
            apprenticeToUpdate.setName(apprentice.getName());
            apprenticeToUpdate.setSurname(apprentice.getSurname());
            apprenticeToUpdate.setPatronymic(apprentice.getPatronymic());
            apprenticeToUpdate.setPhoneNumber(apprentice.getPhoneNumber());
            this.apprenticeRepository.save(apprenticeToUpdate);
            return apprenticeToUpdate;
        } catch (Exception e){
            throw new IllegalArgumentException("Apprentice update failed!");
        }
    }

    @Override
    public void delete(long id) {
        try {
            this.apprenticeRepository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("No apprentice with such ID found!");
        }
    }
}
