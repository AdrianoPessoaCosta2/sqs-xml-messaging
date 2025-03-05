package com.adriano.sqsxmlmessaging.infrastructure.xml;

import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import com.adriano.sqsxmlmessaging.domain.port.MessageHandler;
import com.adriano.sqsxmlmessaging.infrastructure.exception.ErrorMessageService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import com.adriano.sqsxmlmessaging.infrastructure.validator.XmlValidator;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adriano.sqsxmlmessaging.infrastructure.exception.model.ErrorCode.HANDLER_NOT_FOUND;
import static com.adriano.sqsxmlmessaging.infrastructure.exception.model.ErrorCode.UNEXPECTED_ERROR;

@Component
public class MessageFactory {

    private static final Logger logger = LoggerFactory.getLogger(MessageFactory.class);

    private final XStream xstream;
    private final Map<MessageDatatype, MessageHandler<?>> handlerMap;
    private final ErrorMessageService errorMessageService;

    public MessageFactory(XStream xstream, List<MessageHandler<?>> handlers, ErrorMessageService errorMessageService) {
        this.xstream = xstream;
        this.errorMessageService = errorMessageService;
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(MessageHandler::getMessageDataType, handler -> handler));
    }

    public void convertXmlToObject(String xml) throws MessageProcessingException {
        Object message = xstream.fromXML(xml);
        dispatchMessage(message, xml);
    }

    public <T> void dispatchMessage(Object message, String xmlString) throws MessageProcessingException {
        try {
            MessageDatatype messageDatatype = MessageDatatype.fromClass(message.getClass());

            @SuppressWarnings("unchecked")
            MessageHandler<T> handler = (MessageHandler<T>) handlerMap.get(messageDatatype);

            XmlValidator.validateXML(xmlString, handler.getMessageDataType());

            if (handler == null) {
                throw new MessageProcessingException(
                        errorMessageService.getErrorMessage(HANDLER_NOT_FOUND),
                        HANDLER_NOT_FOUND.getCode());
            }
            handler.handle((T) message);

        } catch (MessageProcessingException e) {
            logger.error("Erro inesperado no processamento da mensagem", e);
            throw new MessageProcessingException(
                    errorMessageService.getErrorMessage(UNEXPECTED_ERROR),
                    UNEXPECTED_ERROR.getCode());
        }
    }
}
