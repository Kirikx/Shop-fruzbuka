package ru.fruzbuka.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fruzbuka.persist.repo.PictureRepository;
import ru.fruzbuka.service.impl.PictureServiceBlobImpl;
import ru.fruzbuka.service.impl.PictureServiceFileImpl;

@Configuration
public class PictureServiceConfiguration {

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
    public PictureService pictureServiceBlobImpl(PictureRepository pictureRepository) {
        return new PictureServiceBlobImpl(pictureRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "files")
    public PictureService pictureServiceFileImpl(PictureRepository pictureRepository) {
        return new PictureServiceFileImpl(pictureRepository);
    }
}
