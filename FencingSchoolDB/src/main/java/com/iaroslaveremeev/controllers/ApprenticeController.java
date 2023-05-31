package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Apprentice;
import com.iaroslaveremeev.service.ApprenticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprentice")
public class ApprenticeController {

    private ApprenticeService apprenticeService;

    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService){
        this.apprenticeService = apprenticeService;
    }

    /**
     * Adds a new apprentice and returns this apprentice if successful
     */
    @PostMapping
    public ResponseEntity<ResponseResult<Apprentice>> addApprentice(@RequestBody Apprentice apprentice){
        try {
            this.apprenticeService.add(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(apprentice), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all apprentices
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Apprentice>>> get(){
        try {
            return new ResponseEntity<>(new ResponseResult<>(this.apprenticeService.get()),
                    HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves an apprentice by ID
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> get(@PathVariable long id){
        try {
            return new ResponseEntity<>(new ResponseResult<>(this.apprenticeService.get(id)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates an apprentice by ID
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> update(@PathVariable long id,
                                                             @RequestBody Apprentice apprentice){
        try {
            Apprentice apprenticeToUpdate = this.apprenticeService.update(id, apprentice);
            return new ResponseEntity<>(new ResponseResult<>(apprenticeToUpdate),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes an apprentice by ID
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> delete(@PathVariable long id){
        try {
            Apprentice apprenticeToDelete = this.apprenticeService.get(id);
            this.apprenticeService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(apprenticeToDelete),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
