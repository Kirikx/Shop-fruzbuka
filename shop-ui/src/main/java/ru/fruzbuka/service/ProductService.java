package ru.fruzbuka.service;

import ru.fruzbuka.controller.repr.ProductRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductRepr> getAll();

    Optional<ProductRepr> getById(Long id);

    void saveOrUpdate(ProductRepr product) throws IOException;

    void deleteById(Long id);
}
