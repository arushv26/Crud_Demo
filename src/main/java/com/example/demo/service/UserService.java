package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    // constructor injection
    UserService(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    public User saveUser(User user, MultipartFile image)throws IOException {
        if(image != null && !image.isEmpty()){
            user.setImage(image.getBytes());
            user.setImageType(image.getContentType());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
