package ru.fruzbuka.service;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAll();

    Optional<Role> getById (Long id);

    void saveOrUpdate(Role role);

    void deleteById(Long id);

    Role getByName(String name);
}
