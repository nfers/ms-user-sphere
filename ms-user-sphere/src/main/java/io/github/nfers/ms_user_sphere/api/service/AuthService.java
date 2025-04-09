package io.github.nfers.ms_user_sphere.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.nfers.ms_user_sphere.api.response.LoginResponse;
import io.github.nfers.ms_user_sphere.data.repository.UserRepository;
import io.github.nfers.ms_user_sphere.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {
    
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwtUtils;
    
        public Mono<LoginResponse> authenticate(String userName, String password) {
            return userRepository.findByEmail(userName)
                    .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                    .map(user -> new LoginResponse(jwtUtils.generateToken(user.getEmail())))
                    .switchIfEmpty(Mono.error(new RuntimeException("Invalid credentials")));
        }
    
}
