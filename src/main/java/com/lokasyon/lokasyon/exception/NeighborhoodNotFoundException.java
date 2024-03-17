package com.lokasyon.lokasyon.exception;

public class NeighborhoodNotFoundException extends RuntimeException {
    public NeighborhoodNotFoundException(String message,Object... args){
        super(message);
    }
}
