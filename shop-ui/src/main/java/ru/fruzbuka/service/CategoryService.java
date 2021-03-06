package ru.fruzbuka.service;

import ru.fruzbuka.persist.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();

    Optional<Category> getById (Long id);
}
