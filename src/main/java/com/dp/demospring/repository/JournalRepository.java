package com.dp.demospring.repository;

import com.dp.demospring.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
    void deleteById(Optional myId);
}
