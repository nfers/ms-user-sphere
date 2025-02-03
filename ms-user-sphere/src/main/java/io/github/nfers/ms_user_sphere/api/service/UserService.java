package io.github.nfers.ms_user_sphere.api.service;

import io.github.nfers.ms_user_sphere.api.request.UserRequest;
import io.github.nfers.ms_user_sphere.api.response.UserResponse;
import io.github.nfers.ms_user_sphere.data.model.User;
import io.github.nfers.ms_user_sphere.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Flux<UserResponse> findAll() {
        return userRepository.findAll()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getCreatedDate(),
                        user.isActive()
                ));
    }

    public Mono<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserResponse> saveUser(UserRequest userRequest) {

        var user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setCreatedDate(LocalDateTime.now());
        user.setActive(true);


        return userRepository.save(user)
                .map(savedUser -> new UserResponse(
                        savedUser.getId(),
                        savedUser.getName(),
                        savedUser.getEmail(),
                        savedUser.getRole(),
                        savedUser.getCreatedDate(),
                        savedUser.isActive()
                ));
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    public Mono<UserResponse> findById(String id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getCreatedDate(),
                        user.isActive()
                ));
    }
}
