package com.adriano.sqsxmlmessaging.domain.entity;

import com.adriano.sqsxmlmessaging.domain.model.TypeCustomer;
import java.time.LocalDateTime;

public class CustomerEntity {
        private Long customerId;
        private Long customerIdentifier;
        private String name;
        private TypeCustomer typeCustomer;
        private LocalDateTime referenceDate;

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public LocalDateTime getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(LocalDateTime referenceDate) {
        this.referenceDate = referenceDate;
    }
}
