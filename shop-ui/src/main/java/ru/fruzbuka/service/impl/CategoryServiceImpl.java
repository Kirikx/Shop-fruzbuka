package ru.fruzbuka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fruzbuka.controller.aspect.TrackTime;
import ru.fruzbuka.persist.entity.Category;
import ru.fruzbuka.persist.repo.CategoryRepository;
import ru.fruzbuka.service.CategoryService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    @TrackTime
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    @TrackTime
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }
}

