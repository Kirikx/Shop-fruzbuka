package ru.fruzbuka.service;

import ru.fruzbuka.persist.entity.Brand;

import java.util.List;
import java.util.Optional;


public interface BrandService {

    List<Brand> getAll();

    Optional<Brand> getById (Long id);
}
