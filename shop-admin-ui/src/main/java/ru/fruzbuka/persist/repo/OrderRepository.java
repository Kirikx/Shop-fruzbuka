package ru.fruzbuka.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fruzbuka.persist.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
