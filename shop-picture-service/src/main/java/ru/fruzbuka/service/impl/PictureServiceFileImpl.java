package ru.fruzbuka.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import ru.fruzbuka.exceptions.ServerInternalException;
import ru.fruzbuka.persist.entity.Picture;
import ru.fruzbuka.persist.entity.PictureData;
import ru.fruzbuka.persist.repo.PictureRepository;
import ru.fruzbuka.service.PictureService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class PictureServiceFileImpl implements PictureService {

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureRepository pictureRepository;

    public PictureServiceFileImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findById(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return pictureRepository.findById(id)
                .filter(pic -> pic.getPictureData().getFileName() != null)
                .map(pic -> Paths.get(storagePath, pic.getPictureData().getFileName()))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        log.error("Can`t read picture file ", ex);
                        throw new ServerInternalException(ex);
                    }
                });
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(storagePath, fileName))) {
            outputStream.write(picture);
        } catch (IOException ex) {
            log.error("Can`t create picture file ", ex);
        }
        return new PictureData(fileName);
    }
}
