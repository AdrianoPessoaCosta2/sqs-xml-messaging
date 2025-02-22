package com.adriano.sqsxmlmessaging.infrastructure.repository.columns;

public enum InvoiceColumns {
    INVOICE_ID("invoice_id"),
    CUSTOMER_ID("customer_id"),
    TRANSACTION_ID("transaction_id"),
    TYPE_INVOICE("type_invoice"),
    AMOUNT("amount");

    private final String value;

    InvoiceColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
