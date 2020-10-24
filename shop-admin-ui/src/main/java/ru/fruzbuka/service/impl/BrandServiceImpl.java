package ru.fruzbuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.persist.repo.BrandRepository;
import ru.fruzbuka.service.BrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    public Optional<Brand> getById (Long id){
        return brandRepository.findById(id);
    }

    public void saveOrUpdate(Brand brand){
        brandRepository.save(brand);
    }

    public void deleteById(Long id){
        brandRepository.deleteById(id);
    }
}
