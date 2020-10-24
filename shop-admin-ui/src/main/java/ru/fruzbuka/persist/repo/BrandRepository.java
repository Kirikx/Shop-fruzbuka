package ru.fruzbuka.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fruzbuka.persist.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
