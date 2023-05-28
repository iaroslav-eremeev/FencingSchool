package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.User;

import java.util.Optional;

public interface UserService {
    boolean authenticateUser(String login, char[] password);
    void registerUser(User user);
    User get(long id);
    void delete(long id);
}
