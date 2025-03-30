package io.github.nfers.ms_user_sphere.data.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Update;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private List<Role> roles;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate = LocalDateTime.now();
}