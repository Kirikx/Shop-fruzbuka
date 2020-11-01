package ru.fruzbuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.OrderItem;
import ru.fruzbuka.persist.repo.OrderItemRepository;
import ru.fruzbuka.service.OrderItemService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<OrderItem> getById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
