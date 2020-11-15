package ru.fruzbuka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void downloadProductPicture(@PathVariable("pictureId") Long pictureId, HttpServletResponse response) throws IOException {
        log.info("Downloading picture {}", pictureId);

        Optional<String> optional = pictureService.getPictureContentTypeById(pictureId);
        if (optional.isPresent()) {
            response.setContentType(optional.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {
            throw new NotFoundException();
        }
    }
}
