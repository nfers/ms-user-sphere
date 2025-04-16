package io.github.nfers.ms_user_sphere.data.model;

import io.github.nfers.ms_user_sphere.data.RoleName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Builder
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    @Indexed(unique = true)
    private RoleName name;

    public Role(String id, RoleName name) {
        this.id = id;
        this.name = name;
    }

}

