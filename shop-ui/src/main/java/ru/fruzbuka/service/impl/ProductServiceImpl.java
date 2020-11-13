package ru.fruzbuka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fruzbuka.controller.aspect.TrackTime;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.persist.repo.ProductRepository;
import ru.fruzbuka.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    @TrackTime
    public List<ProductRepr> getAll() {
        return productRepository.findAll()
                .stream().map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    @TrackTime
    public Optional<ProductRepr> getById(Long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }
}
