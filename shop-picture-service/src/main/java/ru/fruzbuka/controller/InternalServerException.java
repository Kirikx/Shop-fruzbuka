package ru.fruzbuka.controller;

public class InternalServerException extends RuntimeException {
    public InternalServerException(Throwable cause) {
        super(cause);
    }
}
