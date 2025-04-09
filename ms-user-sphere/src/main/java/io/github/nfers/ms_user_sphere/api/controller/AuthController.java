package io.github.nfers.ms_user_sphere.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nfers.ms_user_sphere.api.request.LoginRequest;
import io.github.nfers.ms_user_sphere.api.response.LoginResponse;
import io.github.nfers.ms_user_sphere.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;


     @PostMapping("/login")
     public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
         return authService.authenticate(loginRequest.getUserName(), loginRequest.getPassword());
     }

}
    