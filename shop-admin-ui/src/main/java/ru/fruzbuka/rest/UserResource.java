package ru.fruzbuka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.persist.entity.User;
import ru.fruzbuka.persist.repo.UserRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/v1/user")
@RestController
public class UserResource {

    private final UserRepository userRepository;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public User findById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Id found in the create request");
        }
        return userRepository.save(user);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deleteById(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ExceptionEntity(User.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        return new ResponseEntity<>(new ExceptionEntity(User.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> emptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return new ResponseEntity<>(new ExceptionEntity(User.class.getSimpleName(), exception.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

}
