package io.github.nfers.ms_user_sphere.data.model;

import io.github.nfers.ms_user_sphere.data.RoleName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private RoleName name;
}

