package io.github.nfers.ms_user_sphere.api.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}