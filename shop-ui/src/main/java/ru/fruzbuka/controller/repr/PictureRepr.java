package ru.fruzbuka.controller.repr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.fruzbuka.persist.entity.Picture;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class PictureRepr implements Serializable {

    private Long id;

    private String name;

    private String contentType;

    public PictureRepr(Picture picture) {
        this.id = picture.getId();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
    }

}
