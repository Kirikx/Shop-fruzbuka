package ru.fruzbuka.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.persist.entity.Category;
import ru.fruzbuka.persist.entity.Product;
import ru.fruzbuka.persist.repo.ProductRepository;
import ru.fruzbuka.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Vegan");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Apple");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name");
        expectedProduct.setCategories(Collections.singletonList(expectedCategory));
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrise(new BigDecimal("12345"));

        when(productRepository.findById(eq(1L))).thenReturn(Optional.of(expectedProduct));

        Optional<Product> opt = productRepository.findById(expectedProduct.getId());

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
        assertEquals(expectedProduct.getCategories().get(0).getName(), opt.get().getCategories().get(0).getName());
        assertEquals(expectedProduct.getBrand().getName(), opt.get().getBrand().getName());


    }

//    private static class MockProductRepository implements ProductRepository {
//    }
}