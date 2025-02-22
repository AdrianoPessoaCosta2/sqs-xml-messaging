package com.adriano.sqsxmlmessaging.application.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("CustomerRegistration")
public class CustomerRegistration implements Serializable {
    private static final long serialVersionUID = 1L;

    @XStreamAlias("Customer")
    private Customer customer;

    @XStreamAlias("Invoices")
    private Invoices invoices;

    public Customer getCustomer() {
        return customer;
    }

    public Invoices getInvoices() {
        return invoices;
    }
}
