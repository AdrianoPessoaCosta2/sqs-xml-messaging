package com.adriano.sqsxmlmessaging.infrastructure.exception;

public class MessageProcessingException extends Exception{
    private String errorCode;

    public MessageProcessingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
