package com.ironhack.erp.userservice.security;


import com.ironhack.erp.userservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  
  @Bean
  public PasswordEncoder passwordEncoder () {
    return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.httpBasic();
    httpSecurity.authorizeRequests()
            .antMatchers("/").permitAll().and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers(HttpMethod.POST, "/salesrep/**").hasAuthority("ADMIN")
            .antMatchers("/**").hasAnyAuthority("ADMIN", "SALES_REP")
            .and().logout().deleteCookies("JSESSIONID");
    httpSecurity.csrf().disable();
    httpSecurity.cors();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowedOrigins("http://localhost:4200", "*")
        ;
      }
    };
  }
}
