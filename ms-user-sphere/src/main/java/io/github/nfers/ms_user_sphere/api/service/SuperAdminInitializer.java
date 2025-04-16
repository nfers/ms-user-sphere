package io.github.nfers.ms_user_sphere.api.service;

import io.github.nfers.ms_user_sphere.data.RoleName;
import io.github.nfers.ms_user_sphere.data.model.Role;
import io.github.nfers.ms_user_sphere.data.model.User;
import io.github.nfers.ms_user_sphere.data.repository.RoleRepository;
import io.github.nfers.ms_user_sphere.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuperAdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${superadmin.email}")
    private String superAdminEmail;

    @Value("${superadmin.password}")
    private String superAdminPassword;

    @Override
    public void run(ApplicationArguments args) {
        roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
                .switchIfEmpty(
                        roleRepository.save(
                                Role.builder()
                                        .name(RoleName.ROLE_SUPER_ADMIN)
                                        .build()
                        )
                )
                .flatMap(superAdminRole ->
                        userRepository.findByEmail(superAdminEmail)
                                .switchIfEmpty(createSuperAdmin(superAdminRole))
                )
                .subscribe(user ->
                        log.info("Super Admin inicializado/verificado: {}", user.getEmail())
                );
    }

    private Mono<User> createSuperAdmin(Role superAdminRole) {
        return userRepository.save(
                User.builder()
                        .name("Super Admin")
                        .email(superAdminEmail)
                        .password(passwordEncoder.encode(superAdminPassword))
                        .roles(List.of(superAdminRole))
                        .active(true)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build()
        );
    }
}