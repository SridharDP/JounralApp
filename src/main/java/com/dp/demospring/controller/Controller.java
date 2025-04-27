package com.dp.demospring.controller;

import com.dp.demospring.entity.Journal;
import com.dp.demospring.entity.User;
import com.dp.demospring.service.JournalService;
import com.dp.demospring.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/JournalEntry")
public class Controller {
@Autowired
 private JournalService journalService;

@Autowired
private UserService userService;
    @GetMapping()
    public ResponseEntity<?> getJournalEntriesByUser()
    {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        String username=context.getName();
        User user = userService.findById(username);
        List<Journal> all = user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public ResponseEntity<Journal> save(@RequestBody Journal journal)
    {
        try {
            Authentication context = SecurityContextHolder.getContext().getAuthentication();
            String username=context.getName();
             journalService.save(journal,username);
             return new ResponseEntity<>(journal,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<Journal> getById(@PathVariable ObjectId myId)
    {    
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        String username=context.getName();
        User user = userService.findById(username);
        List<Journal> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
       if(!collect.isEmpty())
       {
           Journal byId = journalService.findById(myId);
           if(byId!=null)
           {
               return new ResponseEntity<>(byId, HttpStatus.OK);
           }
       }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<Journal> deleteById(@PathVariable ObjectId myId)
    {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        String username=context.getName();
        boolean b = journalService.deleteById(myId, username);
        if(b)
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{Id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId Id,@RequestBody Journal newEntry)
    {
        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        String username=context.getName();
        User user = userService.findById(username);
        List<Journal> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(Id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            Journal byId = journalService.findById(Id);
            if (byId != null) {
                byId.setTitle(newEntry.getTitle() != null && newEntry.getTitle() != "" ? newEntry.getTitle() : byId.getTitle());
                byId.setContent(newEntry.getContent() != null && newEntry.getContent() != "" ? newEntry.getContent() : byId.getContent());
                journalService.save(byId);
                return new ResponseEntity<>(byId, HttpStatus.CREATED);
            }
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
