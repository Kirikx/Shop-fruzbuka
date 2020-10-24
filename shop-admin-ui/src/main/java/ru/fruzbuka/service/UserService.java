package ru.fruzbuka.service;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> getById (Long id);

    void saveOrUpdate(User user);

    void deleteById(Long id);
}
