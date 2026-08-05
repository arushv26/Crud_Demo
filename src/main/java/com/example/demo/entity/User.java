package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String userName;
    private String password;
    private List<String> notes;
    private List<String> roles;
}
