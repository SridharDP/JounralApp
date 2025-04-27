package com.dp.demospring.service;

import com.dp.demospring.entity.Journal;
import com.dp.demospring.entity.User;
import com.dp.demospring.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private UserService userService;
    public List<Journal> findAll() {
        return journalRepository.findAll();
    }
    @Transactional
    public Journal save(Journal journal, String userName) {
        try {
            User user = userService.findById(userName);
            journal.setDate(LocalDate.now());
            Journal savedJournal = journalRepository.save(journal);
            user.getJournalEntries().add(savedJournal);
            userService.save(user);
            return journal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Journal save(Journal journal)
    {
        return journalRepository.save(journal);
    }

    public Journal findById(ObjectId myId) {
        return journalRepository.findById(myId).orElse(null);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String userName) {
        boolean b=false;
        try {
            User user = userService.findById(userName);
             b = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if (b) {
                userService.save(user);
                journalRepository.deleteById(myId);

            }

        } catch (Exception e) {
            System.out.println(e);
            throw  new RuntimeException("An error occured while deleteing journal entry");
        }
        return b;
    }
}
