package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.TrainerSchedule;
import com.iaroslaveremeev.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/schedule")
public class TrainerScheduleController {
    private TrainerScheduleService trainerScheduleService;

    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService){
        this.trainerScheduleService = trainerScheduleService;
    }

    /**
     * •	post – осуществляет добавление расписания для конкретного тренера
     * (принимает в качестве path айди тренера, аргументы – день недели, время начала и время конца)
     * •	get – осуществляет получение расписания для тренера с заданным idTrainer
     * •	put – осуществляет обновление расписания тренера с заданным idTrainer
     * •	delete – осуществляет удаление расписания тренера с заданным idTrainer
     */

    @PostMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> add(@PathVariable long idTrainer,
                                                               @RequestParam String dayOfWeek,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime start,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime end){
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService
                    .addSchedule(idTrainer, dayOfWeek, start, end);
            return new ResponseEntity<>(new ResponseResult<>(trainerSchedule), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> get(@PathVariable long idTrainer){
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.getByTrainerId(idTrainer);
            return new ResponseEntity<>(new ResponseResult<>(trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> update(@PathVariable long idTrainer,
                                                                  @RequestBody TrainerSchedule trainerSchedule){
        try {
            this.trainerScheduleService.update(idTrainer, trainerSchedule);
            TrainerSchedule updated = this.trainerScheduleService.getByTrainerId(idTrainer);
            return new ResponseEntity<>(new ResponseResult<>(updated), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idTrainer}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> delete(@PathVariable long idTrainer){
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.getByTrainerId(idTrainer);
            this.trainerScheduleService.delete(idTrainer);
            return new ResponseEntity<>(new ResponseResult<>(trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
