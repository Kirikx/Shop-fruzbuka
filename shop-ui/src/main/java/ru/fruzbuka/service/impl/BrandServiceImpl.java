package ru.fruzbuka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fruzbuka.aspect.TrackTime;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.persist.repo.BrandRepository;
import ru.fruzbuka.service.BrandService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Transactional
    @TrackTime
    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Transactional
    @TrackTime
    @Override
    public Optional<Brand> getById (Long id){
        return brandRepository.findById(id);
    }
}
