package com.adriano.sqsxmlmessaging.application.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("Customer")
public class Customer  implements Serializable {
    private static final long serialVersionUID = 1L;

    @XStreamAlias("Customer_identifier")
    private Long customerIdentifier;

    @XStreamAlias("Name")
    private String name;

    @XStreamAlias("TypeCustomer")
    private String typeCustumer;

    @XStreamAlias("ReferenceDate")
    private String referenceDate;

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getTypeCustumer() {
        return typeCustumer;
    }

    public String getReferenceDate() {
        return referenceDate;
    }
}
