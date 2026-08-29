package com.example.demo.controller;


import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping()
    public User saveUser(@RequestPart("user") User user, @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return userService.saveUser(user, image);
    }

    @GetMapping("/{username}/notes")
    public List<String> userNotes(@PathVariable String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getNotes();
    }

    @PutMapping("/{username}/addNotes")
    public String addNotes(@PathVariable String username, @RequestBody List<String> notes){
       return userService.addNotes(username,notes);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "The user is deleted";
    }
}
