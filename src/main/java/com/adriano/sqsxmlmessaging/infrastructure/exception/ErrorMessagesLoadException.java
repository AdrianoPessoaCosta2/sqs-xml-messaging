package com.adriano.sqsxmlmessaging.infrastructure.exception;

public class ErrorMessagesLoadException extends Exception{
    public ErrorMessagesLoadException(String message) {
        super(message);
    }

    public ErrorMessagesLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
