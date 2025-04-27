package com.dp.demospring.controller;

import com.dp.demospring.entity.User;
import com.dp.demospring.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers()
    {
        List<User> all = userService.findAll();
        if(!all.isEmpty())
        {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/saveAdmin")
    public ResponseEntity<?> saveAdminUser(@RequestBody User admin)
    {
        User user = userService.saveAdminUser(admin);
        if(user!=null)
        {
            return new ResponseEntity<>(admin,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
