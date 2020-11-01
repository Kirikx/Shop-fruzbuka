package ru.fruzbuka.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fruzbuka.persist.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
