package com.springapp.dao;
import com.springapp.model.User;

import java.util.List;

public interface UserDao {
    boolean registerUser(User user);
    User findUserById(String id);
    List<User> getAllUsers();
    void updateUser(User user);
    int usernameValidation(String username);
    void deleteUser(String id);
}
