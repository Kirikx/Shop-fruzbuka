package ru.fruzbuka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.persist.entity.Picture;
import ru.fruzbuka.persist.entity.Product;
import ru.fruzbuka.persist.repo.ProductRepository;
import ru.fruzbuka.service.PictureService;
import ru.fruzbuka.service.ProductService;
import ru.fruzbuka.service.StockService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final PictureService pictureService;

    private final StockService stockService;

    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService, StockService stockService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
        this.stockService = stockService;
    }

//    @Transactional
//    @Override
//    public List<Product> getAll() {
//        return productRepository.findAll();
//    }

    @Transactional
    @Override
    public List<ProductRepr> getAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .peek(pr -> pr.setCount(stockService.getStockById(pr.getId()).getCount()))
                .collect(Collectors.toList());
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
                logger.info("Product {} file {} size {}", productRepr.getId(), newPicture.getOriginalFilename(), newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes())));
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
