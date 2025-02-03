package io.github.nfers.ms_user_sphere.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdDate;
    private boolean active;
}
