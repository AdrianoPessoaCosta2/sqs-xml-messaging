package com.adriano.sqsxmlmessaging.domain.service;

import com.adriano.sqsxmlmessaging.application.dto.CustomerRegistration;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceService invoiceService;

    @Transactional
    public void save(CustomerRegistration customerRegistration) throws MessageProcessingException {
        final Long customerId = customerService.save(customerRegistration.getCustomer());
        if (Objects.nonNull(customerRegistration.getInvoices())) {
            invoiceService.save(customerRegistration.getInvoices(), customerId);
        }
    }
}
