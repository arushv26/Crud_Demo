package com.example.demo.repository;

import com.example.demo.entity.User;
//import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
