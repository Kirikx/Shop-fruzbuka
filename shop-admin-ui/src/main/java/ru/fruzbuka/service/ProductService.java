package ru.fruzbuka.service;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    Optional<Product> getById (Long id);

    void saveOrUpdate(Product product);

    void deleteById(Long id);
}
