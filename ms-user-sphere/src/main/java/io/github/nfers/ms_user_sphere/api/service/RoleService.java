package io.github.nfers.ms_user_sphere.api.service;

import io.github.nfers.ms_user_sphere.data.RoleName;
import io.github.nfers.ms_user_sphere.data.model.Role;
import io.github.nfers.ms_user_sphere.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> validateRoles(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Roles não podem ser vazias");
        }
        return roles;
    }

    public Mono<List<Role>> convertRoleNamesToRoles(List<String> roleNames) {
        return Flux.fromIterable(roleNames)
                .flatMap(roleName -> roleRepository
                        .findByName(RoleName.valueOf(roleName)))
                .collectList()
                .flatMap(roles -> {
                    if (roles.size() != roleNames.size()) {
                        return Mono.error(
                                new IllegalArgumentException("Uma ou mais roles não existem"));
                    }
                    return Mono.just(roles);
                });
    }
}
