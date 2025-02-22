package com.adriano.sqsxmlmessaging.domain.mapper;

import com.adriano.sqsxmlmessaging.application.dto.Invoice;
import com.adriano.sqsxmlmessaging.domain.entity.InvoiceEntity;
import com.adriano.sqsxmlmessaging.domain.model.TypeInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = TypeInvoice.class)
public interface InvoiceMapper {
    InvoiceMapper INTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(target = "typeInvoice", expression = "java(TypeInvoice.fromValue(invoice.getTypeInvoice()))")
    InvoiceEntity toEntity(Invoice invoice);

    List<InvoiceEntity> toEntity(List<Invoice> invoices);

    default List<InvoiceEntity> toEntityList(List<Invoice> invoices) {
        return invoices.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
