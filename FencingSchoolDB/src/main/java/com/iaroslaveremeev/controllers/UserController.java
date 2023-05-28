package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.model.User;
import com.iaroslaveremeev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestParam("login") String login,
                                             @RequestParam("password") String password) {
        if (this.userService.authenticateUser(login, password.toCharArray())){
            return ResponseEntity.ok("Login successful!");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam("login") String login,
                                          @RequestParam("password") String password,
                                          @RequestParam("name") String name) {
        try {
            User user = new User(login, password.toCharArray(), name);
            this.userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to register user");
        }
    }
}
