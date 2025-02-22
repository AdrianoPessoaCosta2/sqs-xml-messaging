package com.adriano.sqsxmlmessaging.infrastructure.repository.mapper;

import com.adriano.sqsxmlmessaging.domain.entity.CustomerEntity;
import com.adriano.sqsxmlmessaging.domain.model.TypeCustomer;
import com.adriano.sqsxmlmessaging.infrastructure.repository.columns.CustomerColumns;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerEntityRowMapper implements RowMapper<CustomerEntity> {
    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(rs.getLong(CustomerColumns.CUSTOMER_ID.getValue()));
        customerEntity.setCustomerIdentifier(rs.getLong(CustomerColumns.CUSTOMER_IDENTIFIER.getValue()));
        customerEntity.setName(rs.getString(CustomerColumns.NAME.getValue()));
        customerEntity.setTypeCustomer(TypeCustomer.fromValue(rs.getString(CustomerColumns.TYPE_CUSTOME.getValue())));
        customerEntity.setReferenceDate(rs.getTimestamp(CustomerColumns.REFERENCE_DATE.getValue()).toLocalDateTime());
        return customerEntity;
    }
}
