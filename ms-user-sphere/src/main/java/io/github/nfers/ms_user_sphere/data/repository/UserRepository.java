package io.github.nfers.ms_user_sphere.data.repository;

import io.github.nfers.ms_user_sphere.data.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);

    @Query("{ 'roles.name': 'ROLE_SUPER_ADMIN' }")
    Mono<User> findSuperAdmin();
}
