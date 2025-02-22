package com.adriano.sqsxmlmessaging.domain.handler;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.domain.model.MessageDatatype;
import com.adriano.sqsxmlmessaging.domain.port.MessageHandler;
import com.adriano.sqsxmlmessaging.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.adriano.sqsxmlmessaging.domain.model.MessageDatatype.CUSTOMER;

@Component
public class CustomerHandler implements MessageHandler<Customer> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void handle(Customer customer) {
        customerService.save(customer);
    }

    @Override
    public MessageDatatype getMessageDataType() {
        return CUSTOMER;
    }
}
