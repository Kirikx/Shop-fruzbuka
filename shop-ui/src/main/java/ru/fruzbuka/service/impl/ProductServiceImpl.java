package ru.fruzbuka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fruzbuka.controller.NotFoundException;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.persist.entity.Picture;
import ru.fruzbuka.persist.entity.PictureData;
import ru.fruzbuka.persist.entity.Product;
import ru.fruzbuka.persist.repo.ProductRepository;
import ru.fruzbuka.service.ProductService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    @Override
    public List<ProductRepr> getAll() {
        return productRepository.findAll()
                .stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ProductRepr> getById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProductRepr productRepr) throws IOException {
        Product product = (productRepr.getId() != null) ? productRepository.findById(productRepr.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        product.setName(productRepr.getName());
        product.setDescription(productRepr.getDescription());
        product.setCategories(productRepr.getCategory());
        product.setBrand(productRepr.getBrand());
        product.setPrise(productRepr.getPrice());
        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                log.info("Product {} file {} size {}", productRepr.getId(), newPicture.getOriginalFilename(), newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        new PictureData(newPicture.getBytes())));
            }
        }
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
