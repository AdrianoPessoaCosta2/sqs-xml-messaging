package com.adriano.sqsxmlmessaging.infrastructure.repository;

import com.adriano.sqsxmlmessaging.domain.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {
        Long save (CustomerEntity customer);

        CustomerEntity findByCustomerId(Long customerId);

        Boolean isCustomers(List<Long> customerIdentifier);

        Optional<Long> findIdByCustomerIdentifier(Long customerIdentifier);
}
