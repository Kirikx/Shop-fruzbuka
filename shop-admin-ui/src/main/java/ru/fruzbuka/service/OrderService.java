package ru.fruzbuka.service;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAll();

    Optional<Order> getById (Long id);

    void saveOrUpdate(Order order);

    void deleteById(Long id);
}
