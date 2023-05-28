package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.TrainerSchedule;
import com.iaroslaveremeev.model.User;
import com.iaroslaveremeev.service.TrainerScheduleService;
import com.iaroslaveremeev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/schedule")
public class TrainerScheduleController {

    private TrainerScheduleService trainerScheduleService;

    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService){
        this.trainerScheduleService = trainerScheduleService;
    }
    @PostMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> add(@PathVariable long id,
                                                               @RequestParam String dayOfWeek,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime start,
                                                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime end){
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService
                    .addSchedule(id, dayOfWeek, start, end);
            return new ResponseEntity<>(new ResponseResult<>(trainerSchedule), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
