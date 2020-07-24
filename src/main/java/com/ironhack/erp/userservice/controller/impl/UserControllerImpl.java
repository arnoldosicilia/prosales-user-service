package com.ironhack.erp.userservice.controller.impl;

import com.ironhack.erp.userservice.controller.interfaces.IUserController;
import com.ironhack.erp.userservice.model.entities.User;
import com.ironhack.erp.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserControllerImpl implements IUserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createClient(user);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateClient(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteClient(id);
    }
}
