package com.lokasyon.lokasyon.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String s, Object... args) {
        super(String.format(s, args));
    }
}
