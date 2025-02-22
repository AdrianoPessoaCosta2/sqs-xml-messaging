package com.adriano.sqsxmlmessaging.domain.service;

import com.adriano.sqsxmlmessaging.application.dto.Invoices;
import com.adriano.sqsxmlmessaging.domain.entity.InvoiceEntity;
import com.adriano.sqsxmlmessaging.domain.mapper.InvoiceMapper;
import com.adriano.sqsxmlmessaging.infrastructure.exception.ErrorMessageService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import com.adriano.sqsxmlmessaging.infrastructure.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.adriano.sqsxmlmessaging.infrastructure.exception.model.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ErrorMessageService errorMessageService;

    public void save(final Invoices invoices) throws MessageProcessingException {
        List<InvoiceEntity> invoiceEntities = InvoiceMapper.INTANCE.toEntity(invoices.getInvoices());
        List<Long> customerIds = invoiceEntities.stream()
                .map(InvoiceEntity::getCustomerIdentifier)
                .toList();

        if (!customerService.isCustomers(customerIds)) {
            throw new MessageProcessingException(
                    errorMessageService.getErrorMessage(CUSTOMER_NOT_FOUND),
                    CUSTOMER_NOT_FOUND.getCode());
        }
        save(invoiceEntities);
    }

    public void save(final Invoices invoices, Long idCustomer) throws MessageProcessingException {
        List<InvoiceEntity> invoiceEntities = InvoiceMapper.INTANCE.toEntity(invoices.getInvoices());
        invoiceEntities.forEach(i -> i.setCustomerId(idCustomer));
        save(invoiceEntities);
    }

    @Transactional
    private void save(final List<InvoiceEntity> invoiceEntities) throws MessageProcessingException {
        invoiceRepository.saveAll(invoiceEntities);
    }
}
