package com.adriano.sqsxmlmessaging.domain.handler;

import com.adriano.sqsxmlmessaging.application.dto.Invoices;
import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import com.adriano.sqsxmlmessaging.domain.port.MessageHandler;
import com.adriano.sqsxmlmessaging.domain.service.InvoiceService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.adriano.sqsxmlmessaging.domain.model.MessageDatatype.INVOICES;

@Component
public class InvoiceHandler implements MessageHandler<Invoices> {
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void handle(Invoices invoices) throws MessageProcessingException {
        invoiceService.save(invoices);
    }

    @Override
    public MessageDatatype getMessageDataType() {
        return INVOICES;
    }
}
