package ru.fruzbuka.service;

import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> getAll();

    Optional<OrderItem> getById (Long id);

    void saveOrUpdate(OrderItem orderItem);

    void deleteById(Long id);
}
