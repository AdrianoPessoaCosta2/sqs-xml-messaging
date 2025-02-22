package com.adriano.sqsxmlmessaging.domain.service;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.domain.entity.CustomerEntity;
import com.adriano.sqsxmlmessaging.domain.mapper.CustomerMapper;
import com.adriano.sqsxmlmessaging.infrastructure.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Long save(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toEntity(customer);
        Optional<Long> customerId = customerRepository.findIdByCustomerIdentifier(customerEntity.getCustomerIdentifier());
        return customerId.orElseGet(() -> customerRepository.save(customerEntity));
    }

    public Boolean isCustomers(List<Long> customerIdentifier) {
        return customerRepository.isCustomers(customerIdentifier);
    }
}
