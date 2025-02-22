package com.adriano.sqsxmlmessaging.infrastructure.messaging;

import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import com.adriano.sqsxmlmessaging.infrastructure.xml.MessageFactory;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SqsReceiver {
    private static final Logger logger = LoggerFactory.getLogger(SqsReceiver.class);

    private final MessageFactory messageFactory;

    @Autowired
    public SqsReceiver(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    @SqsListener("main-queue")
    public void listen(@Payload String xml) throws MessageProcessingException {
        logger.debug("Mensagem recebida. ");
        messageFactory.convertXmlToObject(xml);
    }
}
