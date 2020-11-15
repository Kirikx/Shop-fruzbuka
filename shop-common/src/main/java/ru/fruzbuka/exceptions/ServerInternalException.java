package ru.fruzbuka.exceptions;

import java.io.IOException;

public class ServerInternalException extends RuntimeException {
    public ServerInternalException(IOException ex) {
        super(ex);
    }
}
