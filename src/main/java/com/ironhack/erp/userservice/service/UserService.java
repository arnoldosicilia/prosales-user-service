package com.ironhack.erp.userservice.service;

import com.ironhack.erp.userservice.exception.UserNotFoundException;
import com.ironhack.erp.userservice.model.entities.User;
import com.ironhack.erp.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){ return userRepository.findAll();}
    public User findById(Long id){ return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id: "+ id + " could not be found."));}
    public User findByUsername(String username ){ return userRepository.findByUsername(username);}
    public User createClient(User user){ return userRepository.save(user);}
    public User updateClient(Long id, User user) {
        User target = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User with id: "+ id + " could not be found."));

        target.setCompanyId(user.getCompanyId());

        return userRepository.save(target);
    }
    public void deleteClient(Long id){ userRepository.deleteById(id);}
}
