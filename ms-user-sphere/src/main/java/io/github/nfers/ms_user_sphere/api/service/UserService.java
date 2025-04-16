package io.github.nfers.ms_user_sphere.api.service;

import io.github.nfers.ms_user_sphere.api.request.UserRequest;
import io.github.nfers.ms_user_sphere.api.response.UserResponse;
import io.github.nfers.ms_user_sphere.data.model.User;
import io.github.nfers.ms_user_sphere.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Flux<UserResponse> findAll() {
        return userRepository.findAll()
                .map(user -> new UserResponse(
                        user.getName(),
                        user.getEmail(),
                        user.getRoles().stream()
                                .map(role -> role.getName().toString())
                                .collect(Collectors.toList()),
                        user.getCreatedDate(),
                        user.isActive()
                ));
    }


    public Mono<UserResponse> saveUser(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
                .flatMap(__ -> Mono.error(new IllegalArgumentException("Email já existe")))
                .switchIfEmpty(
                        roleService
                                .convertRoleNamesToRoles(userRequest.getRoleNames())
                                .flatMap(roles -> userRepository.save(
                                        User.builder()
                                                .name(userRequest.getName())
                                                .email(userRequest.getEmail())
                                                .password(passwordEncoder
                                                        .encode(userRequest.getPassword()))
                                                .roles(roles)
                                                .createdDate(LocalDateTime.now())
                                                .updatedDate(LocalDateTime.now())
                                                .active(true)
                                                .build()
                                ))
                )
                .cast(User.class)
                .map(UserResponse::from);
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    public Mono<UserResponse> findById(String id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(
                        user.getName(),
                        user.getEmail(),
                        user.getRoles().stream()
                                .map(role -> role.getName().toString())
                                .collect(Collectors.toList()),
                        user.getCreatedDate(),
                        user.isActive()
                ));
    }
}
