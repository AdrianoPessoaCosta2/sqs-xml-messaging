package com.adriano.sqsxmlmessaging.domain.entity;

import com.adriano.sqsxmlmessaging.domain.model.TypeInvoice;

import java.math.BigDecimal;

public class InvoiceEntity {
    private Long invoiceId;
    private Long customerId;
    private Long customerIdentifier;
    private Long transactionId;
    private TypeInvoice typeInvoice;
    private BigDecimal amount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public TypeInvoice getTypeInvoice() {
        return typeInvoice;
    }

    public void setTypeInvoice(TypeInvoice typeInvoice) {
        this.typeInvoice = typeInvoice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
