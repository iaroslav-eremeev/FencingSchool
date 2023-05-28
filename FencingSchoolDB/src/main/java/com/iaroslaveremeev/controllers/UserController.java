package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.User;
import com.iaroslaveremeev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseResult<User>> checkLogin(@RequestParam("login") String login,
                                             @RequestParam("password") String password) {
        try {
            User user = this.userService.get(login, password.toCharArray());
            return new ResponseEntity<>(new ResponseResult<>(user), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseResult<User>> registerUser(@RequestBody User user) {
        try {
            this.userService.registerUser(user);
            return new ResponseEntity<>(new ResponseResult<>(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> get(@PathVariable long id) {
        try {
            User user = this.userService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<User>> delete(@PathVariable long id) {
        try {
            User deletedUser = this.userService.get(id);
            this.userService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(deletedUser), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
