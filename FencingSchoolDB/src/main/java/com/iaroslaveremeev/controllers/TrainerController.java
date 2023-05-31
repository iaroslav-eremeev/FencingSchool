package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Trainer;
import com.iaroslaveremeev.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    /**
     * Adds a new trainer and returns this trainer if successful
     */
    @PostMapping
    public ResponseEntity<ResponseResult<Trainer>> add(@RequestBody Trainer trainer){
        try {
            this.trainerService.add(trainer);
            return new ResponseEntity<>(new ResponseResult<>(trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all trainers
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Trainer>>> get(){
        try {
            List<Trainer> trainers = this.trainerService.get();
            return new ResponseEntity<>(new ResponseResult<>(trainers), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a trainer by ID
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> get(@PathVariable long id){
        try {
            Trainer trainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a trainer by ID
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> put(@PathVariable long id,
                                                       @RequestBody Trainer trainer){
        try {
            this.trainerService.update(id, trainer);
            Trainer updatedTrainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(updatedTrainer), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a trainer by ID
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> delete(@PathVariable long id){
        try {
            this.trainerService.delete(id);
            Trainer deletedTrainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(deletedTrainer), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
