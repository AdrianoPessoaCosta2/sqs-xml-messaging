package com.adriano.sqsxmlmessaging.domain.model;

import java.util.stream.Stream;

public enum TypeCustomer {
    RETAILER("R"), WHOLESALER("W");

    private final String value;

    TypeCustomer(String value) {
        this.value = value;
    }

    public static TypeCustomer fromValue(String value) {
        return Stream.of(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format("TypeCustomer not found: %s", value)));
    }

    public String getValue() {
        return value;
    }
}
