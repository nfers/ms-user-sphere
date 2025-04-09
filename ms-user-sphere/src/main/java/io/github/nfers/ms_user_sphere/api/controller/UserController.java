package io.github.nfers.ms_user_sphere.api.controller;

import io.github.nfers.ms_user_sphere.api.request.UserRequest;
import io.github.nfers.ms_user_sphere.api.response.UserResponse;
import io.github.nfers.ms_user_sphere.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1//users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<UserResponse> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<UserResponse> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}