package com.springapp.service;

import com.springapp.dao.UserDao;
import com.springapp.dao.UserDaoImpl;
import com.springapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired private UserDao userDao;
    org.springframework.security.crypto.password.PasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

    @Override
    public boolean addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.registerUser(user);
    }

    @Override
    public int usernameValidation(String name){
        return userDao.usernameValidation(name);
    }

    @Override
    public User findUserByUsername (String id){return userDao.findUserById(id);}

    @Override
    public List<User> getAllUsers(){
        List<User> users = userDao.getAllUsers();
    return users;
    }

    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String id){
        userDao.deleteUser(id);
    }
}
