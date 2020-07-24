package com.ironhack.erp.userservice.security;

import com.ironhack.erp.userservice.model.entities.Role;
import com.ironhack.erp.userservice.model.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {
  private static final long serialVersionUID = -4381938875186527688L;


  public CustomSecurityUser(User user) {

    System.out.println(user);
    this.setRoles(user.getRoles());
    this.setId(user.getId());
    this.setPassword(user.getPassword());
    this.setUsername(user.getUsername());
    this.setCompanyId(user.getCompanyId());
  }





  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new HashSet<>();
    Set<Role> roles = this.getRoles();
    System.out.println(this);
    for( Role role : roles ) {

      System.out.println(role.getRole());
      authorities.add( new SimpleGrantedAuthority(role.getRole()) );
    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
