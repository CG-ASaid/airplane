package com.example.airplane.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No airplane found.")
public class NotFoundException extends RuntimeException{

}

