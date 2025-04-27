package com.dp.demospring.service;

import com.dp.demospring.entity.User;
import com.dp.demospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

   @Autowired
   public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUser(username);
        if(user!=null)
        {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getUser())
                    .password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found "+username);
    }


}
