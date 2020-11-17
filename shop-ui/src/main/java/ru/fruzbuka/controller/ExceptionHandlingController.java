package ru.fruzbuka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.exceptions.ServerInternalException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.getModel().put("entityName", exception.getEntityName());
        return modelAndView;
    }

    @ExceptionHandler
    public ModelAndView internalServerExceptionHandler(ServerInternalException ex) {
        ModelAndView modelAndView = new ModelAndView("internal_server_error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }
}
