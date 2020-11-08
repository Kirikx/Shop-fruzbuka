package ru.fruzbuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Category;
import ru.fruzbuka.persist.repo.CategoryRepository;
import ru.fruzbuka.service.CategoryService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
