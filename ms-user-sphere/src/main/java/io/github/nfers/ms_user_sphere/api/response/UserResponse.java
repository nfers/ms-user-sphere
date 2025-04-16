package io.github.nfers.ms_user_sphere.api.response;

import io.github.nfers.ms_user_sphere.data.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private List<String> roles;
    private LocalDateTime createdDate;
    private boolean active;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(role -> role.getName().toString())
                        .collect(Collectors.toList()),
                user.getCreatedDate(),
                user.isActive()
        );
    }
}
