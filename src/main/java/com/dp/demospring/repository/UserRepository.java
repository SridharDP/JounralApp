package com.dp.demospring.repository;

import com.dp.demospring.entity.Journal;
import com.dp.demospring.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    void deleteById(Optional myId);

    User findByUser(String username);


    void deleteByUser(String user);
}
