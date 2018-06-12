package com.springapp.service;

import com.springapp.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    int usernameValidation(String name);
    User findUserByUsername (String id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String id);
}
