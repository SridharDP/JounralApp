package com.dp.demospring.configuration;

import com.dp.demospring.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfigProd {
    private final UserDetailServiceImpl userDetailService;

    public SecurityConfigProd(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }
  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
  {

      http.csrf(AbstractHttpConfigurer::disable).
              authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin(AbstractAuthenticationFilterConfigurer::permitAll // Enable default login page
                      )
                      .logout(LogoutConfigurer::permitAll // Enable logout
                      )
                      .httpBasic(httpBasic -> httpBasic.realmName("DemoSpring")); // Optional: Enable HTTP Basic Authentication for APIs
      return http.build();

  }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
