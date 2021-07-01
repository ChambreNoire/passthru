package com.chambrenoire.passthru.exception;

@SuppressWarnings("unused")
public class IllegalConfigurationException extends RuntimeException {

    public IllegalConfigurationException(String message) {
        super(message);
    }

    public IllegalConfigurationException() {
    }
}
