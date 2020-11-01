package ru.fruzbuka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.persist.repo.BrandRepository;
import ru.fruzbuka.service.BrandService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    @Transactional
    public Optional<Brand> getById (Long id){
        return brandRepository.findById(id);
    }

    @Transactional
    public void saveOrUpdate(Brand brand){
        brandRepository.save(brand);
    }

    @Transactional
    public void deleteById(Long id){
        brandRepository.deleteById(id);
    }
}
