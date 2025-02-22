package com.adriano.sqsxmlmessaging.domain.handler;

import com.adriano.sqsxmlmessaging.application.dto.CustomerRegistration;
import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import com.adriano.sqsxmlmessaging.domain.port.MessageHandler;
import com.adriano.sqsxmlmessaging.domain.service.CustomerRegistrationService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.adriano.sqsxmlmessaging.domain.model.MessageDatatype.CUSTOMER_REGISTRATION;

@Component
public class CustomerRegistrationHandler implements MessageHandler<CustomerRegistration> {
    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Override
    public void handle(CustomerRegistration customerRegistration) throws MessageProcessingException {
        customerRegistrationService.save(customerRegistration);
    }

    @Override
    public MessageDatatype getMessageDataType() {
        return CUSTOMER_REGISTRATION;
    }
}
