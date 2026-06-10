package edu.ifal.virtable.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import edu.ifal.virtable.security.JwtAuthFilter;

@TestConfiguration
public class TestSecurityConfig extends SecurityConfig {
  public TestSecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService) {
    super(jwtAuthFilter, userDetailsService);
  }

}
