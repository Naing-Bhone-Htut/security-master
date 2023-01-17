package com.example.securitymaster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController {

    private Logger logger= LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handErrors(Throwable throwable, Model model)throws Throwable {
//        logger.info(throwable.getCause().getMessage());
        String msg=throwable != null ? throwable.getMessage() : "unknown error.";
        model.addAttribute("errorMessage",msg);
        return "error";
    }
}
