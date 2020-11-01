package ru.fruzbuka.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fruzbuka.persist.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
