package com.adriano.sqsxmlmessaging.infrastructure.exception;

public class ErrorMessagesLoadRuntimeException extends RuntimeException{
    public ErrorMessagesLoadRuntimeException(String message) {
        super(message);
    }

    public ErrorMessagesLoadRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
