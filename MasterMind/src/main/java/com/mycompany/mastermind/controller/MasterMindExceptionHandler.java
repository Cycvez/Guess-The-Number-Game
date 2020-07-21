/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.controller;

import com.mycompany.mastermind.service.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author carlo
 */
@ControllerAdvice
@RestController
public class MasterMindExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Error> handleDataError(InvalidDataException ex, WebRequest request) {
        String message = ex.getMessage();
        Error error = new Error(message);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

    }
}
