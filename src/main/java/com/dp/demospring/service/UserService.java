package com.dp.demospring.service;

import com.dp.demospring.entity.User;
import com.dp.demospring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

  //  private static final Logger log= LoggerFactory.getLogger(UserService.class);

    public static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        log.info("Saving user");
        log.debug("saving user debug",user);
        return userRepository.save(user);
    }
    public User saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        log.info("Saving user");
        return userRepository.save(user);
    }


    public User findById(String name) {
        return userRepository.findByUser(name);
    }

    public void deleteById(ObjectId myId) {
        userRepository.deleteById(myId);
    }


    public void deleteByUserName(String user) {
        userRepository.deleteByUser(user);
    }

    public User saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User","Admin"));
        return userRepository.save(user);
    }
}
