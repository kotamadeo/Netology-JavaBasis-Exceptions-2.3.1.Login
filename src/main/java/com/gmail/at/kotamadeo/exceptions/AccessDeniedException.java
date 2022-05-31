package com.gmail.at.kotamadeo.exceptions;

public class AccessDeniedException extends UserNotFoundException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
