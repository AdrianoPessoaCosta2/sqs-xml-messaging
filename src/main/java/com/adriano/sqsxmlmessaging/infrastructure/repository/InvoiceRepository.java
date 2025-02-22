package com.adriano.sqsxmlmessaging.infrastructure.repository;

import com.adriano.sqsxmlmessaging.domain.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceRepository {
    void saveAll(List<InvoiceEntity> invoiceEntity);
}
