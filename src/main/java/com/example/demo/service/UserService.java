package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;
    // constructor injection
    UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public User saveUser(User user){
        return userRepo.save(user);
    }
    public List<User> getAll(){
        return userRepo.findAll();
    }
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
    public User findByUsername(String username){
        return userRepo.findByUserName(username);
    }
    public String addNotes(String username, List<String> notes){
        User existingUser = userRepo.findByUserName(username);
        if( existingUser != null){
            existingUser.getNotes().addAll(notes);
            userRepo.save(existingUser);
            return "Notes Added";
        }
        return "User Not Found";
    }
}
