package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.User;

public interface UserService {
    User get(String login, char[] password);
    void registerUser(User user);
    User get(long id);
    void delete(long id);
}
