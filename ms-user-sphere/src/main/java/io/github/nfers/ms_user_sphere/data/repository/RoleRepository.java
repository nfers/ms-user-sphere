package io.github.nfers.ms_user_sphere.data.repository;

import io.github.nfers.ms_user_sphere.data.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {
    Mono<Role> findByName(String name);

    Flux<Role> findAllRoles();
}
