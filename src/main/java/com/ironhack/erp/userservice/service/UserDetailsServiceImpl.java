package com.ironhack.erp.userservice.service;


import com.ironhack.erp.userservice.model.entities.User;
import com.ironhack.erp.userservice.repository.UserRepository;
import com.ironhack.erp.userservice.security.CustomSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    User user = userRepository.findByUsername(username);
    
    if (user == null)
      throw new UsernameNotFoundException("Invalid username/password combination.");
    return new CustomSecurityUser(user);
  }


  
}
