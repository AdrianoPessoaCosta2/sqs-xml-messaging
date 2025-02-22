package com.adriano.sqsxmlmessaging.domain.mapper;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.domain.entity.CustomerEntity;
import com.adriano.sqsxmlmessaging.domain.model.TypeCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = TypeCustomer.class)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "typeCustomer", expression = "java(TypeCustomer.fromValue(customer.getTypeCustumer()))")
    CustomerEntity toEntity(Customer customer);
}
