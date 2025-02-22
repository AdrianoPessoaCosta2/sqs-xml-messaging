package com.adriano.sqsxmlmessaging.domain.port;

import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;

public interface MessageHandler<T> {
    void handle(T message) throws MessageProcessingException;
    MessageDatatype getMessageDataType();
}