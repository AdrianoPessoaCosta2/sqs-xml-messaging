package com.adriano.sqsxmlmessaging.infrastructure.repository.columns;

public enum CustomerColumns {
    CUSTOMER_ID("customer_id"),
    CUSTOMER_IDENTIFIER("customer_identifier"),
    NAME("name"),
    TYPE_CUSTOME("type_customer"),
    REFERENCE_DATE("reference_date"),
    SIZE("size");

    private final String value;

    CustomerColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
