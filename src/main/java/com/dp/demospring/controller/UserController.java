package com.dp.demospring.controller;

import com.dp.demospring.entity.Journal;
import com.dp.demospring.entity.User;
import com.dp.demospring.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserEntry")
public class UserController {
@Autowired
 private UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll()
    {
        List<User> all = userService.findAll();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{name}")
    public ResponseEntity<User> getById(@PathVariable String name)
    {
        User byId = userService.findById(name);
        if(byId!=null)
        {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<Journal> deleteById(@PathVariable ObjectId myId)
    {
        userService.deleteById(myId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping()
    public ResponseEntity<Journal> deleteByUserName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateByName(@RequestBody User newEntry)
    {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        String username=context.getName();
        User existingUser = userService.findById(username);
        if (existingUser != null) {
            // Update password only if provided and non-empty
            if (newEntry.getPassword() != null && !newEntry.getPassword().isEmpty()) {
                existingUser.setPassword(newEntry.getPassword());
            }
            // Update roles if provided
            if (newEntry.getRoles() != null && !newEntry.getRoles().isEmpty()) {
                existingUser.setRoles(newEntry.getRoles());
            }
            userService.saveNewUser(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
