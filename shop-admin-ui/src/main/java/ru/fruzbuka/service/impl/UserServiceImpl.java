package ru.fruzbuka.service.impl;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.User;
import ru.fruzbuka.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveOrUpdate(User user) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
