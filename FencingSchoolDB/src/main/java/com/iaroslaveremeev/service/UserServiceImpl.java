package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.User;
import com.iaroslaveremeev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticateUser(String login, char[] password) {
        User user = userRepository.getUserByLogin(login);
        if (user != null && Arrays.equals(user.getPassword(), password)) {
            // Clear the password array to remove sensitive information
            Arrays.fill(password, ' ');
            return true;
        }
        else {
            // Clear the password array even if the login is invalid
            Arrays.fill(password, ' ');
            return false; // Login is invalid
        }
    }

    @Override
    public void registerUser(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User is already added!");
        }
    }

    @Override
    public Optional<User> get(long id) {
        try {
            return this.userRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("User with this id does not exist");
        }
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
