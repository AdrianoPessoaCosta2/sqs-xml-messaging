package com.adriano.sqsxmlmessaging.application.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("Invoices")
public class Invoices  implements Serializable {
    private static final long serialVersionUID = 1L;

    @XStreamImplicit(itemFieldName = "Invoice")
    private List<Invoice> invoices;

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
