package com.adriano.sqsxmlmessaging.domain.model;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.application.dto.CustomerRegistration;
import com.adriano.sqsxmlmessaging.application.dto.Invoices;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;

import java.util.stream.Stream;

public enum MessageDatatype {
    CUSTOMER_REGISTRATION(CustomerRegistration.class),
    INVOICES(Invoices.class),
    CUSTOMER(Customer.class);

    private final Class<?> dtoClass;

    MessageDatatype(Class<?> dtoClass) {
        this.dtoClass = dtoClass;
    }

    public static MessageDatatype fromClass(Class<?> clazz) throws MessageProcessingException {
        return Stream.of(values())
                .filter(v -> clazz == v.dtoClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MessageDatatype não encontrada"));
    }

    public String getDtoClass(){
        return dtoClass.getSimpleName();
    }
}
