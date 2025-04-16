package io.github.nfers.ms_user_sphere.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@NotNull
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private List<String> roleNames;
}

