package com.adriano.sqsxmlmessaging.application.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.math.BigDecimal;

@XStreamAlias("Invoice")
public class Invoice  implements Serializable {
    private static final long serialVersionUID = 1L;

    @XStreamAlias("Customer_identifier")
    private Long customerIdentifier;

    @XStreamAlias("Transaction_id")
    private Long transactionId;

    @XStreamAlias("TypeInvoice")
    private String typeInvoice;

    @XStreamAlias("Amount")
    private BigDecimal amount;

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getTypeInvoice() {
        return typeInvoice;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
