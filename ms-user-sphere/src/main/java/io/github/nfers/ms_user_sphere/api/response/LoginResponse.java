package io.github.nfers.ms_user_sphere.api.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}