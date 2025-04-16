package io.github.nfers.ms_user_sphere.data.repository;

import io.github.nfers.ms_user_sphere.data.RoleName;
import io.github.nfers.ms_user_sphere.data.model.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {
    Mono<Role> findByName(String name);

    Flux<Role> findAllByName(List<String> names);

    Mono<Role> findByName(RoleName name);

}
