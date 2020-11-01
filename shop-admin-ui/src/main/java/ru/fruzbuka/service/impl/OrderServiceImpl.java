package ru.fruzbuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Order;
import ru.fruzbuka.persist.repo.OrderRepository;
import ru.fruzbuka.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
