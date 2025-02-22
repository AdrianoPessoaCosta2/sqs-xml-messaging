package com.adriano.sqsxmlmessaging.domain.model;


import java.util.stream.Stream;

public enum TypeInvoice {
    BOOK("B"), LEISURE("L"), FOOD("F"), FUEL("U"), OTHERS("O");

    TypeInvoice(String value) {
        this.value = value;
    }

    private final String value;

    public static TypeInvoice fromValue(String value) {
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
