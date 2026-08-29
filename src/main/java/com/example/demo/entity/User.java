package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//@Document(collection = "user")
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @ElementCollection
    private List<String> notes = new ArrayList<>();
    @ElementCollection
    private List<String> roles = new ArrayList<>();

    @Lob
    private byte[] image;

    private String imageType;
}
