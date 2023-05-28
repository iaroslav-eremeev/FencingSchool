package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.User;
import com.iaroslaveremeev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(String login, char[] password) {
        User user = userRepository.getUserByLogin(login);
        if (user != null && Arrays.equals(user.getPassword(), password)) {
            // Clear the password array to remove sensitive information
            Arrays.fill(password, ' ');
            return user;
        }
        else {
            // Clear the password array even if the login is invalid
            Arrays.fill(password, ' ');
            return null; // Login is invalid
        }
    }

    @Override
    public void registerUser(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User registration failed!");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with such ID does not exist!"));
    }

    @Override
    public void delete(long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("No user with such ID found!");
        }
    }
}
