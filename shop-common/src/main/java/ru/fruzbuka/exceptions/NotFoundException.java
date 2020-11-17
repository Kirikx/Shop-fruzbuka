package ru.fruzbuka.exceptions;

public class NotFoundException extends RuntimeException {
    private String entityName;

    public NotFoundException() {
    }

    public NotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
