package com.ironhack.erp.userservice.controller.interfaces;

import com.ironhack.erp.userservice.exception.UserNotFoundException;
import com.ironhack.erp.userservice.model.entities.User;

import java.util.List;

public interface IUserController {
    public List<User> findAll();
    public User findById(Long id);
    public User createUser(User user);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
}
