package ru.fruzbuka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.controller.NotFoundException;
import ru.fruzbuka.persist.entity.Product;
import ru.fruzbuka.persist.repo.ProductRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {


    private final ProductRepository productRepository;

    @Autowired
    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Product findById(@PathVariable("id") Long id) {
        return productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createUser(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id found in the create request");
        }
        return productRepository.save(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateUser(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteById(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ExceptionEntity(Product.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        return new ResponseEntity<>(new ExceptionEntity(Product.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> emptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return new ResponseEntity<>(new ExceptionEntity(Product.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }
}
