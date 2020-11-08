package ru.fruzbuka.controller.repr;

import ru.fruzbuka.persist.entity.Category;

import java.io.Serializable;

public class CategoryRepr implements Serializable {

    private long id;

    private String name;

    private String description;

    public CategoryRepr(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategoryRepr{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
